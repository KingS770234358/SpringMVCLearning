package com.wq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestFulController {

    // 最原始的风格 http://localhost:8080/04ControllerDetail/add?a=1&b=2
    // 请求URL中的参数名一定要和对应的方法中形参名一模一样,否则500错误
    // 不带参数的URL也会 500 错误
    @RequestMapping("/add")
    public String test1(int a, int b, Model model){
        int res = a+b;
        model.addAttribute("msg","结果为:"+res);
        return "hello";
    }
    // RestFul风格:http://localhost:8080/04ControllerDetail/add/1/2
    // 这里方法使用的@PathVariable变量名 一定要和 RequestMapping花括号中填写的一直 否则500
    // url请求中的参数类型一定要和方法定义的形参类型一致 否则400错误

    // 注解RequestMapping的name 可能就是给这个映射起个名字而已 与url路径无关
    // value = path 匹配url中的参数
    // method定义方法处理的提交类型 RequestMethod.GET/POST/PUT/DELETE等等
    // 点@RequestMapping查看源码,他是一个枚举类
    // public enum RequestMethod {
    //    GET,
    //    HEAD,
    //    POST,
    //    PUT,
    //    PATCH,
    //    DELETE,
    //    OPTIONS,
    //    TRACE;
    //    private RequestMethod() {
    //    }
    //}

    // @RequestMapping(name="MappingName", value = "/add/{a}/{b}", method = RequestMethod.GET)
    // 更为简化的写法[只有以GET方式提交的add请求才会被该方法处理]
    // @PostMapping @DeleteMapping @PutMapping @GetMapping @PatchMapping
    @GetMapping(value = "/add/{a}/{b}")
    public String test2(@PathVariable int a, @PathVariable int b, Model model){
        int res = a+b;
        model.addAttribute("msg","结果为:"+res);
        return "hello";
    }
//    // 当存在两个处理的提交方式相同 url /add部分也相同的时候 即使方法传入的形参类型不同 redeploy也会失败
//    // ====>SpringMVC不会通过形参的数据类型来判断使用哪个方法
//    @GetMapping(value = "/add/{a}/{b}")
//    public String test3(@PathVariable int a, @PathVariable String b,Model model){
//        int res = a;
//        model.addAttribute("msg","结果为:"+res);
//        return "hello";
//    }
    // 当存在两个处理的提交方式相同 url /add部分也相同的时候 允许形参个数不一样的方法存在 redeploy成功
    @GetMapping(value = "/add/{a}/{b}/{c}")
    public String test4(@PathVariable int a, @PathVariable int b,@PathVariable String c,Model model){
        String res = a+b+c;
        model.addAttribute("msg","结果为:"+res);
        return "hello";
    }
    // 在web下(而不是WEB-INF下)写一个能直接访问的表单测试POST提交方式
    @PostMapping(value = "/add/{a}/{b}")
    public String test5(@PathVariable int a, @PathVariable int b,Model model){
        // POST提交和GET提交结果明显不同
        int res = a+b+100000;
        model.addAttribute("msg","结果为:"+res);
        return "hello";
    }
}
