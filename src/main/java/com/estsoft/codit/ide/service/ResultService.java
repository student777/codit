package com.estsoft.codit.ide.service;

import com.estsoft.codit.db.repository.*;
import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.db.vo.ProblemVo;
import com.estsoft.codit.db.vo.ResultVo;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.ide.executor.ExecCompiler;
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
  /*
  1) result 테이블에서 applicantVo에 해당하는 결과값 가져온다
  2) 텍스트로 쏴줄 거는 jsp에 넣어준다
  3-a) 그래프 사진 generate 해서 링크를 걸어준다
  3-b) 아니면 그래프에 들어갈 숫자만 jsp로 넘긴 다음 javascript로 그래프를 그려준다
       즉, front-end 단에서 시각화(렌더링)을 해줌
 */
  public void getResult(Model model, ApplicantVo applicantVo) {
    //set applicantVo
    int applicantId = applicantVo.getId();
    model.addAttribute("applicantVo", applicantVo);

    //set result
    List<Integer> problemInfoIdList = problemInfoRepository.getByApplicantId(applicantId);
    List<List<ResultVo>> resultListOfList = new ArrayList<List<ResultVo>>();
    for (int problemInfoId:problemInfoIdList ) {
      List<ResultVo> resultList = new ArrayList<ResultVo>();
      List<Integer> testCaseIdList = testCaseRepository.getByProblemInfoId2(problemInfoId);
      for(int testCaseId:testCaseIdList) {
        resultList.add(resultRepository.getByApplicantAndTestCase(applicantId, testCaseId));
      }
      resultListOfList.add(resultList);
    }
    model.addAttribute("resultListOfList", resultListOfList);
  }

  public void getTree(Model model, ApplicantVo applicantVo, int index) {
    //get SourceCode
    List<SourceCodeVo> sourceCodeVoList = sourceCodeRepository.getByApplicant(applicantVo.getId());
    if(index > sourceCodeVoList.size() || index < 1){
      model.addAttribute("sourcecode", "BAD REQUEST");
      model.addAttribute("json", "BAD REQUEST");
      return;
    }
    SourceCodeVo sourceCodeVo = sourceCodeVoList.get(index-1);
    ProblemVo problemVo = problemRepository.get(sourceCodeVo.getProblemId());
    int languageId = languageRepository.getByProblemId(sourceCodeVo.getProblemId());
    if(problemVo.getLanguageId()==2){
      ExecCompiler execCompiler = new ExecCompiler(sourceCodeVo);
      String json = execCompiler.run();
      model.addAttribute("sourcecode", sourceCodeVo.getCode().replace("\r\n", "\n"));
      model.addAttribute("language_id", languageId);
      model.addAttribute("json", json);
    }
    else{
      model.addAttribute("sourcecode", sourceCodeVo.getCode());
      model.addAttribute("json", "{info: \"C, python은 준비중입니다\"}");
      model.addAttribute("language_id", languageId);
    }
  }
}
