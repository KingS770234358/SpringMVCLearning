package com.wq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/handlerInterceptor")
public class HandlerInterceptorController {
    @RequestMapping("/test")
    public String test(){
        System.out.println("interceptor test");
        return "test";
    }
    @RequestMapping("/goLogin")
    public String goLogin(){
        return "login";
    }
    @RequestMapping("/goMain")
    public String goMain(){
        return "main";
    }
    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password, Model model){
        System.out.println(username+password);
        session.setAttribute("username",username);
        model.addAttribute("username",username);
        return "main";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        // 按键删除键值对
        // 直接摧毁session的方式不好
        // session.invalidate(); // 直接摧毁
        session.removeAttribute("username");
        // 删除用户信息之后 退出到入口页面 就无法在进入主界面了
        return "login";
    }
}
