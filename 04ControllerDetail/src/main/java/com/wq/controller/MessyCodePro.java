package com.wq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试乱码问题用的Controller
 * */
@Controller
@RequestMapping("/MessyCodePro")
public class MessyCodePro{

    // 表单使用POST提交 所以这里直接用PostMapping 否则405
    @GetMapping("/test1")
    public String MessyCodetest1(String name, Model model){
        // 无效:思路1 使用HttpServletRequest对象的setCharacterEncoding的方式解决
        // 这里已经乱码
        System.out.println("查看name是否乱码:"+name);
        model.addAttribute("msg", name);
        return "hello";
    }

}
