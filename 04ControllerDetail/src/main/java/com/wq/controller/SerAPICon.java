package com.wq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/***
 * 注释掉视图解析器
 * 使用ServletAPI来实现控制器
 */
@Controller
public class SerAPICon {
    @RequestMapping("/ServAPI/test1ReqAndRSP")
    public String test1(HttpServletRequest request, HttpServletResponse response){
        HttpSession httpSession = request.getSession();
        // 测试成功
        System.out.println(httpSession.getId());

        // 在注释掉视图解析器的情况下 要return视图的全限定名
        // return "hello";
        return "/WEB-INF/jsp/hello.jsp";
    }
    // 给视图传一些参数进行设置
    // 测试转发
    @RequestMapping("/ServAPI/testModel")
    public String test2(Model model){

        model.addAttribute("msg","Servle...t!");
        // 在注释掉视图解析器的情况下 要return视图的全限定名
        // return "hello";
        //return "/WEB-INF/jsp/hello.jsp";
        // 上述的return方式和下面等价
        return "forward:/WEB-INF/jsp/hello.jsp";
    }

    // 测试重定向 在浏览器输入url可以看到地址栏输入的url瞬间改变
    @RequestMapping("/ServAPI/testRedirect")
    public String testRedirect(Model model){

        model.addAttribute("msg","redirect");
        // 重定向 相当于浏览器再次发起一个请求 不能访问WEB-INF下的资源
        return "redirect:/index.jsp";
    }
}
