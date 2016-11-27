package com.estsoft.codit.ide.service;

import com.estsoft.codit.db.repository.*;
import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.db.vo.ResultVo;

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

    public void getDetailResult(Model model, ApplicantVo applicantVo, int problemInfoId) {
        //set applicantVo to model
        int applicantId = applicantVo.getId();
        model.addAttribute("applicantVo", applicantVo);

        //set result
        List<ResultVo> resultList = new ArrayList<ResultVo>();
        List<Integer> testCaseIdList = testCaseRepository.getByProblemInfoId2(problemInfoId);
        for (int testCaseId : testCaseIdList) {
            resultList.add(resultRepository.getByApplicantAndTestCase(applicantId, testCaseId));
        }
        model.addAttribute("resultList", resultList);
    }
}
