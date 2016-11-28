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
        String secretKey = request.getParameter("secret_key");
        ApplicantVo applicantVo = applicantRepository.getBySecretKey(secretKey);
        if (applicantVo == null) {
            return true;
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("authApplicant", applicantVo);
        }
        return true;
    }
}
