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
    int language_id = 1;
    List<Integer> problemInfoIdList = problemInfoRepository.getPracticeList();
    List<ProblemInfoVo> problemInfoList = new ArrayList<ProblemInfoVo>();
    List<ProblemVo> problemList = new ArrayList<ProblemVo>();
    List<List<TestCaseVo>> testcaseList = new ArrayList<List<TestCaseVo>>();
    for (int problem_info_id:problemInfoIdList ) {
      problemInfoList.add(problemInfoRepository.get(problem_info_id));
      problemList.add(problemRepository.getByProblemInfoId(problem_info_id, language_id));
      testcaseList.add(testCaseRepository.getByProblemInfoId(problem_info_id));
    }
    model.addAttribute("problemInfoVoList", problemInfoList ) ;
    model.addAttribute("problemList", problemList);
    model.addAttribute("testcaseListList", testcaseList);
  }
}
