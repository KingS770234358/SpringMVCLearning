package com.wq.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("username")!=null){
            return true;
        }
        if(request.getRequestURI().contains("ogin")){
            return true;
        }
        // 返回去一个页面
        //request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        request.getRequestDispatcher("/handlerInterceptor/goLogin").forward(request,response);
        return false;
    }
}
