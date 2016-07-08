package com.estsoft.codit.ide.service;

import com.estsoft.codit.db.repository.ApplicantRepository;
import com.estsoft.codit.db.repository.ProblemInfoRepository;
import com.estsoft.codit.db.repository.ProblemRepository;
import com.estsoft.codit.db.repository.TestCaseRepository;
import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.db.vo.ProblemInfoVo;
import com.estsoft.codit.db.vo.ProblemVo;
import com.estsoft.codit.db.vo.TestCaseVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

  @Autowired
  private ApplicantRepository applicantRepository;

  @Autowired
  private ProblemInfoRepository problemInfoRepository;

  @Autowired
  private ProblemRepository problemRepository;

  @Autowired
  private TestCaseRepository testCaseRepository;

  /*
   uuid를 받아 applicant 를 식별해서 applicant 정보를 넣어준다
   */
  public boolean checkTicket(String ticket, Model model) {
    ApplicantVo vo = applicantRepository.getByTicket(ticket);
    if(vo==null){
      return false;
    }
    model.addAttribute("applicant", vo);
    return true;
  }

  /*
  practice 할떄 쓰는 더미데이터 설정
   */
  public void setProblem(Model model){
    //init variables
    List<ProblemInfoVo> problemInfoList = new ArrayList<ProblemInfoVo>();
    List<List<ProblemVo>> problemListOfList = new ArrayList<List<ProblemVo>>();
    List<List<TestCaseVo>> testcaseListOfList = new ArrayList<List<TestCaseVo>>();
    int totalTime = 600;

    //set all problems
    List<Integer> problemInfoIdList = problemInfoRepository.getPracticeList();
    for (int problemInfoId:problemInfoIdList ) {
      ProblemInfoVo problemInfoVo = problemInfoRepository.get(problemInfoId);
      List<ProblemVo> problemVoList = problemRepository.getByProblemInfoId(problemInfoId);
      List<TestCaseVo> testCaseVoList = testCaseRepository.getByProblemInfoId(problemInfoId);
      problemInfoList.add(problemInfoVo);
      problemListOfList.add(problemVoList);
      testcaseListOfList.add(testCaseVoList);
      model.addAttribute("totalTime", totalTime);
    }
    model.addAttribute("problemInfoList", problemInfoList ) ;
    model.addAttribute("problemListOfList", problemListOfList);
    model.addAttribute("testcaseListOfList", testcaseListOfList);
  }
}
