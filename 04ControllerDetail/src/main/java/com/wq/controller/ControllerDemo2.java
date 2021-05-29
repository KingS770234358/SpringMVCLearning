package com.wq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 解释@Controller
 * 代表这个类会被Spring托管
 * · Spring中四个等效的注解 都代表一个组件
 * @Component 组件
 * @Service service层
 * @Controller Controller层
 * @Repository Dao层
 * 这个类里的所有方法,如果返回类型是String 并且有具体的页面就可以跳转
 * 就会被视图解析器解析
  */

@Controller
public class ControllerDemo2 {
    @RequestMapping("/Demo2")
    public String test1(Model model){
        // mdoel对象时接收请求时SpringMVC自动给的
        // 是将数据部分从ModelAndView对象里面分离出来

        model.addAttribute("msg","类上没有RequestMapping的Demo2");
        // return的时候model里的信息也会提交给相关的页面进行渲染
        return "hello"; // WEB-INF/jsp/hello.jsp
    }

    /**
     * 这样实现了jsp页面的复用
     * 控制器和视图之间弱耦合
     */
    @RequestMapping("/Demo3")
    public String test2(Model model){
        // mdoel对象时接收请求时SpringMVC自动给的
        // 是将数据部分从ModelAndView对象里面分离出来

        model.addAttribute("msg","类上没有RequestMapping的Demo3");
        // return的时候model里的信息也会提交给相关的页面进行渲染
        return "hello"; // WEB-INF/jsp/hello.jsp
    }
}
