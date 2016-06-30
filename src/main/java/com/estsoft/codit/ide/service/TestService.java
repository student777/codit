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
public class TestService {

  @Autowired
  private ProblemInfoRepository problemInfoRepository;

  @Autowired
  private ProblemRepository problemRepository;

  @Autowired
  private TestCaseRepository testCaseRepository;

  @Autowired
  private ApplicantRepository applicantRepository;
  /**
   * Save.
   */
/*
  (시험 중)
   applicant의 소스코드를 받아 저장해줌
   */
  public void save() {
  }



/*
   (시험 중)저장된 소스코드를 compile and run
   채점은 안한다
   */
  public void run() {

    /*
    1) 컴파일
    2) 소스코드에 해당되는 테스트 케이스 끌어와 run
    3) 런타임시 생성되는 메시지를 출력
    4) 채점 결과 저장


     */

  }


/*
   (최종 제출 후)저장된 소스코드를 compile and run
   채점후 결과값을 저장한다
   */
  public void mark() {

    /*
    1) 컴파일
    2) 소스코드에 해당되는 테스트 케이스 끌어와 run
    3) 런타임시 생성되는 메시지를 출력
    4) 채점 결과 저장
     */
  }

  /*
  applicant에 해당되는 문제 풀을 설정해줌
   */
  public void initializeTest(Model model, ApplicantVo applicantVo, int language_id){
    //init variables
    List<ProblemInfoVo> problemInfoList = new ArrayList<ProblemInfoVo>();
    List<ProblemVo> problemList = new ArrayList<ProblemVo>();
    List<List<TestCaseVo>> testcaseList = new ArrayList<List<TestCaseVo>>();
    int totalTime = 0;

    //set problem
    List<Integer> problemInfoIdList = problemInfoRepository.getByApplicantId( applicantVo.getId() );
    for (int problem_info_id:problemInfoIdList ) {
      ProblemInfoVo problemInfoVo = problemInfoRepository.get(problem_info_id);
      ProblemVo problemVo = problemRepository.getByProblemInfoId(problem_info_id, language_id);
      List<TestCaseVo> testCaseVo = testCaseRepository.getByProblemInfoId(problem_info_id);
      problemInfoList.add(problemInfoVo);
      problemList.add(problemVo);
      testcaseList.add(testCaseVo);
      totalTime += problemInfoVo.getEstimated_time();
    }
    model.addAttribute("problemInfoVoList", problemInfoList ) ;
    model.addAttribute("problemList", problemList);
    model.addAttribute("testcaseListList", testcaseList);
    model.addAttribute("totalTime", totalTime);

    //set timer
    applicantRepository.setStartTime(applicantVo);
  }
}
