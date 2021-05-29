package com.wq.controller;

import com.wq.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/getParam")
public class getParamController {
    /***
     * 这种情况下,如果前端传来的URL参数和形参名不一样,则取值为空
     */
    @RequestMapping("/testGetSameParam")
    // /getParam/testGetSameParam
    public String testGetSameParam(String name, Model model){
        // 1.接收前端参数
        System.out.println("接收到的前端参数为:"+ name);

        // 2.将返回的参数传递给前端 使用Model对象
        model.addAttribute("msg",name);

        // 3.跳转视图
        return "hello";
    }

    /***
     * 当前端url中传入的参数与方法的形参名不一样的时候需要使用
     * 这个注解:@RequestParam("username") 前端要传这个字段只能以username传
     * 如果前端不按username来传这个参数会报 400 错误
     * 注意:不论如何都加上就对了,可以标识是前端要用的(传来的)参数
     */
    @RequestMapping("/testRequestParam")
    // /getParam/testRequestParam
    public String testRequestParam(@RequestParam("username") String name, Model model){
        // 1.接收前端参数
        System.out.println("接收到的前端参数为名为:username,值为"+ name);

        // 2.将返回的参数传递给前端 使用Model对象
        model.addAttribute("msg",name);

        // 3.跳转视图
        return "hello";
    }

    /***
     * 测试前端传入一个对象的情况
     * User 类 id name age
     * 这种情况下,前端url中传入的参数名要和User类每个字段都一一对应,都要一样
     * 如果有那个参数名和它对应的User里面的属性名不一致,则这个值为null或0
     * 只要参数名和属性名一样,url中参数的位置可以随意!
     */
    @RequestMapping("/testPassObject")
    // /getParam/testPassObject?id=66&name=wjk&age=12
    public String testPassObject(User u, Model model){
        // 1.接收前端参数
        System.out.println("接收到的前端参数一个对象,值为"+ u);

        // 2.将返回的参数传递给前端 使用Model对象
        model.addAttribute("msg",u.getName());

        // 3.跳转视图
        return "hello";
    }

}
