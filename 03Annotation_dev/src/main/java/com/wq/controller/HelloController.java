package com.wq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 采用注解开发controller
 * 1.加上@Controller之后就会被spring的ComponentScan自动扫描到
 * 相当于原来Spring配置中的这一步
 * <bean id="/hello" class="com.wq.controller.HelloController"/>
 *
 * 2.在类里就可以编写任意的方法了,而不像之前只能实现接口Controller的方法
 * · 封装数据
 * · 返回视图名称
 * ====>这样视图和数据就会自动绑定返回给HandlerAdapter,
 * 最后再给视图解析器 解析 渲染
 *
 * 3.用注解实现url和方法的映射
 *   这里的url是从配置tomcat时的application context开始算起
 *   在这个类上也可以使用@RequestMapping("/hello")
 *   类里的方法@RequestMapping("/h1") url就都要拼接上前缀/hello
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    // ap = [配置tomcat时的application context]
    // url: localhost:8080[ap]/hello/h1
    @RequestMapping("/h1")
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
