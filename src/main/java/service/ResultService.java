package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import repository.ProblemInfoRepository;
import repository.ResultRepository;
import repository.SourceCodeRepository;
import repository.TestCaseRepository;
import vo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private SourceCodeRepository sourceCodeRepository;

    @Autowired
    private ProblemInfoRepository problemInfoRepository;

    @Autowired
    private TestCaseRepository testCaseRepository;

    public void getDetailResult(Model model, ApplicantVo applicantVo, int problemInfoId) {
        // get last sourceCode
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applicantId", applicantVo.getId());
        map.put("problemInfoId", problemInfoId);
        SourceCodeVo sourceCodeVo = sourceCodeRepository.getByApplicantAndProbleminfo(map);
        // when sourceCodeVo == null, handle at result_detail.jsp

        // get last resultList
        List<ResultVo> resultVoList = resultRepository.getBySourceCode(sourceCodeVo);
        model.addAttribute("resultList", resultVoList);
        ProblemInfoVo problemInfoVo = problemInfoRepository.get(problemInfoId);
        model.addAttribute("problemInfo", problemInfoVo);
        model.addAttribute("sourceCode", sourceCodeVo);

        // get recordList and testCaseList
        List<Map<String, Object>> recordList1 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> recordList2 = new ArrayList<Map<String, Object>>();
        boolean isPublicOnly = false;
        List<TestCaseVo> testCaseList = testCaseRepository.getByProblemInfoId(problemInfoId, isPublicOnly);
        for(TestCaseVo testCaseVo: testCaseList){
            //exclude result correctness=false, memory=-1, runningTime=-1)
            Map<String, Object> record1 = resultRepository.getRecordsByTestCaseAndMemory(testCaseVo); // null when record not exists
            Map<String, Object> record2 = resultRepository.getRecordsByTestCaseAndRunningTime(testCaseVo); // null when record not exists
            recordList1.add(record1);
            recordList2.add(record2);
        }
        model.addAttribute("recordList1", recordList1);
        model.addAttribute("recordList2", recordList2);
    }
}