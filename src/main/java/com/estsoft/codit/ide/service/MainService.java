package com.estsoft.codit.ide.service;

import com.estsoft.codit.db.repository.ApplicantRepository;
import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.ide.annotation.AuthApplicant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

  @Autowired
  private ApplicantRepository applicantRepository;

  /*
   uuid를 받아 applicant 를 식별하고 해당 request에 세션을 부여
  public void idenfityApplicant(String ticket, Model model) {
    ApplicantVo vo = applicantRepository.getByTicket(ticket);
    model.addAttribute(vo);
  }
   */

  public boolean checkEmail(String email, @AuthApplicant ApplicantVo applicantVo) {
    ApplicantVo vo = applicantRepository.getByEmail(email);
    if(vo==null){
      return false;
    }
    return vo.getId()==applicantVo.getId();
  }
}
