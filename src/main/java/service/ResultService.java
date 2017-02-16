package service;

import repository.*;
import vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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

    public void getDetailResult(Model model, ApplicantVo applicantVo, int problemInfoId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applicantId", applicantVo.getId());
        map.put("problemInfoId", problemInfoId);
        SourceCodeVo sourceCodeVo = sourceCodeRepository.getByApplicantAndProbleminfo(map);
        // when sourceCodeVo == null, handle at result_detail.jsp
        List<ResultVo> resultVoList = resultRepository.getBySourceCode(sourceCodeVo);
        model.addAttribute("resultList", resultVoList);
        ProblemInfoVo problemInfoVo = problemInfoRepository.get(problemInfoId);
        model.addAttribute("problemInfo", problemInfoVo);
        model.addAttribute("sourceCode", sourceCodeVo);
    }
}