package com.wq.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 只要实现了controller函数式接口的类就是一个控制器
 * 只做一件事情,就是返回ModelAndView
 * 注意:和注解方式的控制器导入的包不一样
 */
public class ControllerDemo1 implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mv = new ModelAndView();
        System.out.println("进入恐怖感知器");
        mv.addObject("msg","ControllerDemo1");
        mv.setViewName("hello");
        return mv;
    }
}
