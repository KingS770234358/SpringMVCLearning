package com.wq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 解释@RequestMapping
 */

@Controller
@RequestMapping("/Demo3")
public class ControllerDemo3 {
    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","ControllerDemo3类下的test方法");
        return "hello";
    }
}
