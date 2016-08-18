package com.estsoft.codit.ide.service;

import com.estsoft.codit.db.repository.ApplicantRepository;
import com.estsoft.codit.db.repository.ProblemInfoRepository;
import com.estsoft.codit.db.repository.ProblemRepository;
import com.estsoft.codit.db.repository.ResultRepository;
import com.estsoft.codit.db.repository.SourceCodeRepository;
import com.estsoft.codit.db.repository.TestCaseRepository;
import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.db.vo.ProblemInfoVo;
import com.estsoft.codit.db.vo.ProblemVo;
import com.estsoft.codit.db.vo.ResultVo;
import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;
import com.estsoft.codit.ide.executor.Exec;
import com.estsoft.codit.ide.executor.ExecFactory;
import com.estsoft.codit.ide.executor.ExecResultInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

  @Autowired
  private ProblemInfoRepository problemInfoRepository;

  @Autowired
  private ProblemRepository problemRepository;

  @Autowired
  private TestCaseRepository testCaseRepository;

  @Autowired
  private ApplicantRepository applicantRepository;

  @Autowired
  private SourceCodeRepository sourceCodeRepository;

  @Autowired
  private ResultRepository resultRepository;

  /*
  applicant에 해당되는 문제 풀을 설정해줌
  */
  public void initializeTest(Model model, ApplicantVo applicantVo){
    //init variables
    List<ProblemInfoVo> problemInfoList = new ArrayList<ProblemInfoVo>();
    List<List<ProblemVo>> problemListOfList = new ArrayList<List<ProblemVo>>();
    List<List<TestCaseVo>> testcaseListOfList = new ArrayList<List<TestCaseVo>>();
    int totalTime = 0;
    boolean isPublicOnly = true;

    //set problem
    List<Integer> problemInfoIdList = problemInfoRepository.getByApplicantId( applicantVo.getId() );
    for (int problemInfoId:problemInfoIdList ) {
      ProblemInfoVo problemInfoVo = problemInfoRepository.get(problemInfoId);
      //TODO: 문제시작에 problemVo 다 갖다 reponse로 던져준다. 비동기식으로 안함
      //ProblemVo problemVo = problemRepository.getByProblemInfoLanguageId(problemInfoId, languageId);
      List<ProblemVo> problemVoList = problemRepository.getByProblemInfoId(problemInfoId);
      List<TestCaseVo> testCaseVoList = testCaseRepository.getByProblemInfoId(problemInfoId, isPublicOnly);
      problemInfoList.add(problemInfoVo);
      problemListOfList.add(problemVoList);
      testcaseListOfList.add(testCaseVoList);
      totalTime += problemInfoVo.getEstimatedTime();
    }
    model.addAttribute("problemInfoList", problemInfoList ) ;
    model.addAttribute("problemListOfList", problemListOfList);
    model.addAttribute("testcaseListOfList", testcaseListOfList);
    model.addAttribute("totalTime", totalTime);

    //set timer
    applicantRepository.setStartTime(applicantVo);
  }


  /*
   시험 도중 applicant의 소스코드를 받아 저장해줌
  */
  public boolean save(String code, int problemId, int applicantId) {
    SourceCodeVo sourceCodeVo = new SourceCodeVo();
    sourceCodeVo.setCode(code);
    sourceCodeVo.setApplicantId(applicantId);
    sourceCodeVo.setProblemId(problemId);
    int isInserted = sourceCodeRepository.insert(sourceCodeVo);
    //insert 결과가 실패했을 경우 repository에서 0 리턴
    //성공했을 경우에는 id값에 source_code의 id값이 나옴
    return isInserted==1;
  }


 /*
  1) 컴파일
  2) source_code 테이블에서 최종 저장본을 가져온다
  3) 유저로부터 요청받은 테스트 케이스를 끌어와 compile & run
  4) 런타임시 생성되는 메시지를 출력. 채점은 안한다
 */
  public String run(int problemId, int applicantId, int testCaseId) {
    //applicantId와 ProblemId로 sourceCode를 찾아 가장 최근거를 꺼내욘다
    SourceCodeVo sourceCodeVo = new SourceCodeVo();
    sourceCodeVo.setApplicantId(applicantId);
    sourceCodeVo.setProblemId(problemId);
    sourceCodeVo = sourceCodeRepository.getByApplicantAndProblem(sourceCodeVo);

    //testCaseId에 해당하는 testCaseVo 꺼내오기
    //testCaseId 가 0일 때의 처리
    TestCaseVo testCaseVo;
    if(testCaseId==0){
      testCaseVo = null;
    }
    else{
      testCaseVo = testCaseRepository.get(testCaseId);
    }

    // sourcecode를 task.* 파일로 작상하여 compile & run
    int languageId = problemRepository.get(problemId).getLanguageId();
    Exec exec = new ExecFactory().pick(languageId, sourceCodeVo);
    return exec.run(testCaseVo);
  }

  //TODO: 채점끝나고 redirect시키면 느리니까 먼저 redirect하고 /result에서 기다릴수있게
  public void finalize_test(ApplicantVo applicantVo) {
    //set submit_time
    applicantRepository.setSubmitTime(applicantVo);

    //set permission
  }

  public void mark(ApplicantVo applicantVo){
    int applicantId = applicantVo.getId();

    //get TestCase
    List<List<TestCaseVo>> testcaseListOfList = new ArrayList<List<TestCaseVo>>();
    List<Integer> problemInfoIdList = problemInfoRepository.getByApplicantId( applicantId );
    for (int problemInfoId:problemInfoIdList ) {
      boolean isPublicOnly = false;
      List<TestCaseVo> testCaseVoList = testCaseRepository.getByProblemInfoId(problemInfoId, isPublicOnly);
      testcaseListOfList.add(testCaseVoList);
    }
    //get SourceCode
    List<SourceCodeVo> sourceCodeVoList = sourceCodeRepository.getByApplicant(applicantId);

    //TODO: sourcdCodeVo와 testCaseVo problem_info_id가 같은지 체크
    for (int i = 0; i < sourceCodeVoList.size(); i++) {
      SourceCodeVo sourceCodeVo = sourceCodeVoList.get(i);
      List<TestCaseVo> testCaseVoList = testcaseListOfList.get(i);
      int languageId = problemRepository.get(sourceCodeVo.getProblemId()).getLanguageId();
      Exec exec = new ExecFactory().pick(languageId, sourceCodeVo);

      // 하나의 Exec 객체는 한 개의 sourceCode를 받아 생성되고 n개의 test_case를 처리한다
      // test case가 없는 문제의 경우 for문을 안탄다. 모든 문제는 test_case가 있다고 가정
      for (TestCaseVo testCaseVo: testCaseVoList ) {
        ResultVo resultVo = new ResultVo();
        //TODO: input()같은게 많아서 안끝나는 프로세스의 경우 mark가 안되서 뒤에있는거까지 다 안됨
        ExecResultInfo execResultInfo = exec.mark(testCaseVo);
        resultVo.setApplicantId(applicantId);
        resultVo.setTestCaseId(testCaseVo.getId());

        //set correctness
        if(testCaseVo.getAnswer().equals(execResultInfo.getOutput())){
          resultVo.setCorrectness(true);
        }
        else{
          resultVo.setCorrectness(false);
        }

        //set time, memory
        resultVo.setUsedMemory(execResultInfo.getUsedMemory());
        resultVo.setRunningTime(execResultInfo.getRunningTime());

        //add to DB
        resultRepository.insert(resultVo);
      }
    }
  }
}
