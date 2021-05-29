package com.wq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class TestController {
    // ap = [配置tomcat时的application context]
    // url: localhost:8080[ap]/hello/h1
    @RequestMapping("/h1")
    @ResponseBody
    public String hello1(Model model){

        // 执行业务逻辑

        // 通过Model参数来封装数据
        model.addAttribute("msg","hello1方法");

        // 这里返回的字符串,就是交给HandlerAdapter的视图名称,供视图解析器解析
        // 当使用@RestController时, 返回的就是单纯的字符串,不会被视图解析器解析
        // 这里return hello 点击左边行号,可以跳转到对应的页面
        return "hello";
    }
    @RequestMapping("/h2")
    public String hello2(Model model){

        // 执行业务逻辑

        // 通过Model参数来封装数据
        model.addAttribute("msg","hello2方法");

        // 这里返回的字符串,就是交给HandlerAdapter的视图名称,供视图解析器解析
        // 这里return hello 点击左边行号,可以跳转到对应的页面
        return "hello";
    }
}
