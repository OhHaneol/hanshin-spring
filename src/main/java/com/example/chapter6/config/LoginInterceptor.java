package com.example.chapter6.config;

import com.example.chapter6.model.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//  @Bean 은 개발자가 작성한 메소드를 Bean 으로 만드는 것이고, @Component는 Class 를 Bean으로 만드는 것.
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        logger.info("로그인 인터셉트 ={}", memberVO);

        if(memberVO != null) {
            return true;
        } else {
            response.sendRedirect("/membere/login");
            return false;
        }
    }
}
