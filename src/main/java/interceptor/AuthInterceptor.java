package interceptor;

import vo.ApplicantVo;
import annotation.Auth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
        if (auth == null) {
            return true;
        }

        HttpSession session = request.getSession();
        if (session == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/invalid_session.jsp");
            rd.forward(request, response);
            return false;
        }

        ApplicantVo applicantVo = (ApplicantVo) session.getAttribute("authApplicant");
        if (applicantVo == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/invalid_session.jsp");
            rd.forward(request, response);
            return false;
        }

        // 인증된 사용자
        return true;
    }
}
