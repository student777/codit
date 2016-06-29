package com.estsoft.codit.ide.service;

import com.estsoft.codit.db.repository.ApplicantRepository;
import com.estsoft.codit.db.vo.ApplicantVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MainService {

  @Autowired
  private ApplicantRepository applicantRepository;

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
}
