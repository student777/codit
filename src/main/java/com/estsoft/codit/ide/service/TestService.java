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

    //get problem list by problem_info id
    public void initializeTest(Model model, int problemInfoId) {
        boolean isPublicOnly = true; //only public when tested, both public and private when marked
        ProblemInfoVo problemInfoVo = problemInfoRepository.get(problemInfoId);
        List<ProblemVo> problemVoList = problemRepository.getByProblemInfoId(problemInfoId);
        List<TestCaseVo> testCaseVoList = testCaseRepository.getByProblemInfoId(problemInfoId, isPublicOnly);
        model.addAttribute("problemInfoVo", problemInfoVo);
        model.addAttribute("problemList", problemVoList);
        model.addAttribute("testcaseList", testCaseVoList);
        model.addAttribute("totalTime", problemInfoVo.getEstimatedTime());
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
        return isInserted == 1;
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
        ProblemVo problemVo = problemRepository.get(problemId);

        //testCaseId에 해당하는 testCaseVo 꺼내오기
        //testCaseId 가 0일 때의 처리
        TestCaseVo testCaseVo;
        if (testCaseId == 0) {
            testCaseVo = null;
        } else {
            testCaseVo = testCaseRepository.get(testCaseId);
        }
        // sourcecode를 task.* 파일로 작상하여 compile & run
        int languageId = problemRepository.get(problemId).getLanguageId();
        Exec exec = new ExecFactory().pick(languageId, sourceCodeVo, problemVo);
        return exec.run(testCaseVo);
    }


    public void mark(ApplicantVo applicantVo, int problemId) {
        int applicantId = applicantVo.getId();

        //get TestCase list
        int problemInfoId = problemInfoRepository.getByProblemId(problemId);
        boolean isPublicOnly = false;
        List<TestCaseVo> testCaseVoList = testCaseRepository.getByProblemInfoId(problemInfoId, isPublicOnly);

        //get SourceCode
        SourceCodeVo sourceCodeVo = new SourceCodeVo();
        sourceCodeVo.setProblemId(problemId);
        sourceCodeVo.setApplicantId(applicantId);
        sourceCodeVo = sourceCodeRepository.getByApplicantAndProblem(sourceCodeVo);

        // mark
        ProblemVo problemVo = problemRepository.get(sourceCodeVo.getProblemId());
        int languageId = problemVo.getLanguageId();
        Exec exec = new ExecFactory().pick(languageId, sourceCodeVo, problemVo);

        // test case가 없는 문제의 경우 for문을 안탄다. 모든 문제는 test_case가 있다고 가정
        for (TestCaseVo testCaseVo : testCaseVoList) {
            ResultVo resultVo = new ResultVo();
            ExecResultInfo execResultInfo = exec.mark(testCaseVo);
            resultVo.setApplicantId(applicantId);
            resultVo.setTestCaseId(testCaseVo.getId());

            //set correctness
            if ("Correct Answer!".equals(execResultInfo.getOutput().replace("\n", ""))) {
                resultVo.setCorrectness(true);
            } else {
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
