package com.estsoft.codit.ide.interceptor;

import com.estsoft.codit.db.repository.ApplicantRepository;
import com.estsoft.codit.db.vo.ApplicantVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InstructionInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private ApplicantRepository applicantRepository;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String ticket = request.getParameter("ticket");
    String email = request.getParameter("email");
    ApplicantVo applicantVoByTicket = applicantRepository.getByTicket(ticket);
    ApplicantVo applicantVoByEmail = applicantRepository.getByRecruitIdEmail(applicantVoByTicket.getRecruitId() , email);
    if(applicantVoByTicket==null || applicantVoByEmail==null){
      return true;
    }
    if(applicantVoByTicket.getId()==applicantVoByEmail.getId()){
      HttpSession session = request.getSession(true);
      session.setAttribute("authApplicant", applicantVoByTicket );
    }
    return true;
  }
}
