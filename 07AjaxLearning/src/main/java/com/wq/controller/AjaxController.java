package com.wq.controller;
/***
 * 测试Ajax的Controller
 *
 */

import com.wq.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class AjaxController {

    @RequestMapping("/test1")
    public String test1(){
        return "/ajaxtTest.jsp";
    }

    @RequestMapping("/a1")
    // 以键取值 data:{"name":$("#username").val()},
    // rsp.getWriter().print("true");要抛出异常
    public void test1(String name, HttpServletResponse rsp) throws IOException {
        System.out.println("a1:param==>"+name);
        if("wq".equals(name)){
            rsp.getWriter().print("true");
        }else{
            rsp.getWriter().print("false");
        }
    }

    @RequestMapping("/testJsonAjax")
    @ResponseBody // 这样就可以不走是视图解析器
    public List<User> testJsonAjax(){
        List<User> userList = new ArrayList<User>();
        // 添加数据
        userList.add(new User("wanga",1,"男"));
        userList.add(new User("ljj",1,"男"));
        userList.add(new User("zjl",1,"男"));
        // [{"name":"wanga","age":1,"sex":"男"},{"name":"ljj","age":1,"sex":"男"},{"name":"zjl","age":1,"sex":"男"}]
        return userList;

    }
    @RequestMapping("/checkUser")
    @ResponseBody // 这样就可以不走是视图解析器
    public List<String> checkUser(String name, String pwd){
        String usermsg = "";
        String pwdmsg = "";
        System.out.println(name+pwd);
        if("admin".equals(name)){
            usermsg = "ok";
        }else {
            usermsg = "nonono";
        }
        if("123456".equals(pwd)){
            pwdmsg="ok";
        }else {
            pwdmsg="pwd nonono";
        }
        List<String> infoList = new ArrayList<String>();
        infoList.add(usermsg);
        infoList.add(pwdmsg);
        return infoList;
    }

}
