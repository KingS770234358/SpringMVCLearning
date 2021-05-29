package com.wq.controller;
/***
 * 用来操作事务的Controller
 */

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        // ModelAndView 封装模型(数据)和视图的对象(容器/Map)
        ModelAndView mv = new ModelAndView();
        System.out.println("进入控制器了啊啊啊啊啊");

        // 封装Model(数据),放在ModelAndView对象(容器/Map)中
        mv.addObject("msg","HelloSpringMVC!");
        // 封装要跳转的View视图
        // 会被DispatcherServlet用视图解析器解析成完整路径
        mv.setViewName("hello");

        // 返回该视图对象(给DispatcherServlet)
        return mv;
    }
}
