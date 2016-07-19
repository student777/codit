package com.estsoft.codit.ide.service;

import com.estsoft.codit.db.repository.ProblemInfoRepository;
import com.estsoft.codit.db.repository.ResultRepository;
import com.estsoft.codit.db.repository.TestCaseRepository;
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
}
