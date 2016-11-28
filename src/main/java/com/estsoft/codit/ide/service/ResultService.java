package com.estsoft.codit.ide.service;

import com.estsoft.codit.db.repository.*;
import com.estsoft.codit.db.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ProblemInfoRepository problemInfoRepository;

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private SourceCodeRepository sourceCodeRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public void getDetailResult(Model model, ApplicantVo applicantVo, int problemId) {
        //set applicantVo to model
        int applicantId = applicantVo.getId();
        model.addAttribute("applicantVo", applicantVo);
        model.addAttribute("problemId", problemId);

        //set result
        int problemInfoId = problemInfoRepository.getByProblemId(problemId);
        List<ResultVo> resultList = new ArrayList<ResultVo>();
        boolean isPublicOnly = false;
        SourceCodeVo sourceCodeVo = new SourceCodeVo();
        sourceCodeVo.setApplicantId(applicantId);
        sourceCodeVo.setProblemId(problemId);
        sourceCodeVo = sourceCodeRepository.getByApplicantAndProblem(sourceCodeVo);
        List<TestCaseVo> testCaseVoList = testCaseRepository.getByProblemInfoId(problemInfoId, isPublicOnly);
        for (TestCaseVo testCaseVo : testCaseVoList) {
            resultList.add(resultRepository.getBySourceCodeTestCase(sourceCodeVo.getId(), testCaseVo.getId()));
        }
        model.addAttribute("resultList", resultList);
    }
}
