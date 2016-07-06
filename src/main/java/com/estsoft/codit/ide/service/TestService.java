package com.estsoft.codit.ide.service;

import com.estsoft.codit.db.repository.ApplicantRepository;
import com.estsoft.codit.db.repository.ProblemInfoRepository;
import com.estsoft.codit.db.repository.ProblemRepository;
import com.estsoft.codit.db.repository.SourceCodeRepository;
import com.estsoft.codit.db.repository.TestCaseRepository;
import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.db.vo.ProblemInfoVo;
import com.estsoft.codit.db.vo.ProblemVo;
import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;
import com.estsoft.codit.ide.util.ExecSourceCode;
import com.estsoft.codit.ide.util.WriteFile;

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

  /*
applicant에 해당되는 문제 풀을 설정해줌
 */
  public void initializeTest(Model model, ApplicantVo applicantVo){
    //init variables
    List<ProblemInfoVo> problemInfoList = new ArrayList<ProblemInfoVo>();
    List<List<ProblemVo>> problemListOfList = new ArrayList<List<ProblemVo>>();
    List<List<TestCaseVo>> testcaseListOfList = new ArrayList<List<TestCaseVo>>();
    int totalTime = 0;

    //set problem
    List<Integer> problemInfoIdList = problemInfoRepository.getByApplicantId( applicantVo.getId() );
    for (int problemInfoId:problemInfoIdList ) {
      ProblemInfoVo problemInfoVo = problemInfoRepository.get(problemInfoId);
      //TODO: 일단 problemVo 다 갖다 붙인다. 비동기식으로 안함
      //ProblemVo problemVo = problemRepository.getByProblemInfoLanguageId(problemInfoId, languageId);
      List<ProblemVo> problemVoList = problemRepository.getByProblemInfoId(problemInfoId);
      List<TestCaseVo> testCaseVoList = testCaseRepository.getByProblemInfoId(problemInfoId);
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
  (시험 중)
   applicant의 소스코드를 받아 저장해줌
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
    2) 소스코드에 해당되는 테스트 케이스 끌어와 run
    3) 런타임시 생성되는 메시지를 출력
   채점은 안한다
   */
  public String run(int problemId, int applicantId) {
    //applicantId와 ProblemId로 sourceCode를 찾아 가장 최근거를 꺼내욘다
    SourceCodeVo sourceCodeVo = new SourceCodeVo();
    sourceCodeVo.setApplicantId(applicantId);
    sourceCodeVo.setProblemId(problemId);
    sourceCodeVo = sourceCodeRepository.getByApplicantAndProblem(sourceCodeVo);

    // code값을 main_code와 함께 컴파일 후 돌림
    // TODO: 저장 되고 나서 해야하는데 안그런다(?) 잘되는거 같기도 함
    ProblemVo problemVo = problemRepository.get(problemId);
    int languageId = problemVo.getLangguageId();
    WriteFile writeFile = new WriteFile();
    writeFile.write(sourceCodeVo, languageId);
    ExecSourceCode execSourceCode = new ExecSourceCode();
    String runtimeOutput;
    if(languageId==1){
      runtimeOutput = execSourceCode.execC(sourceCodeVo);
    }
    else if(languageId==2){
      runtimeOutput = execSourceCode.execJava(sourceCodeVo);
    }
    else if(languageId==3){
      runtimeOutput = execSourceCode.execPython(sourceCodeVo);
    }
    else{
      runtimeOutput = "";
    }
    return runtimeOutput;
  }


/*
   (최종 제출 후)저장된 소스코드를 compile and run
   채점후 결과값을 저장한다
   */
  public void mark() {

    /*
    1) 컴파일
    2) 소스코드에 해당되는 테스트 케이스 끌어와 run
    3) 런타임시 생성되는 메시지를 출력
    4) 채점 결과 저장
     */
  }


}
