package service;

import repository.*;
import vo.*;
import executor.Exec;
import executor.ExecFactory;
import executor.ExecResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    private SourceCodeRepository sourceCodeRepository;

    @Autowired
    private ResultRepository resultRepository;

    //get problem list by problem_info id
    public void initializeTest(Model model, int problemInfoId) {
        boolean isPublicOnly = true; //only public when tested, both public and private when marked
        ProblemInfoVo problemInfoVo = problemInfoRepository.get(problemInfoId);
        List<ProblemVo> problemVoList = problemRepository.getByProblemInfoId(problemInfoId);
        List<TestCaseVo> testCaseVoList = testCaseRepository.getByProblemInfoId(problemInfoId, isPublicOnly);
        model.addAttribute("problemInfo", problemInfoVo);
        model.addAttribute("problemList", problemVoList);
        model.addAttribute("testcaseList", testCaseVoList);
        model.addAttribute("totalTime", problemInfoVo.getEstimatedTime());
    }


    public String run(String code, int problemId, int testCaseId) {
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
        Exec exec = new ExecFactory().pick(languageId, code, problemVo);
        return exec.run(testCaseVo);
    }


    public void mark(ApplicantVo applicantVo, String code, int problemId) {
        // save source_code to DB
        int applicantId = applicantVo.getId();
        SourceCodeVo sourceCodeVo = new SourceCodeVo();
        sourceCodeVo.setCode(code);
        sourceCodeVo.setApplicantId(applicantId);
        sourceCodeVo.setProblemId(problemId);
        sourceCodeRepository.insert(sourceCodeVo);


        //get TestCase list
        int problemInfoId = problemInfoRepository.getByProblemId(problemId);
        boolean isPublicOnly = false;
        List<TestCaseVo> testCaseVoList = testCaseRepository.getByProblemInfoId(problemInfoId, isPublicOnly);

        // mark
        ProblemVo problemVo = problemRepository.get(problemId);
        int languageId = problemVo.getLanguageId();
        Exec exec = new ExecFactory().pick(languageId, code, problemVo);

        // test case가 없는 문제의 경우 for문을 안탄다. 모든 문제는 test_case가 있다고 가정
        for (TestCaseVo testCaseVo : testCaseVoList) {
            ResultVo resultVo = new ResultVo();
            ExecResultInfo execResultInfo = exec.mark(testCaseVo);
            resultVo.setSourceCodeId(sourceCodeVo.getId());
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
