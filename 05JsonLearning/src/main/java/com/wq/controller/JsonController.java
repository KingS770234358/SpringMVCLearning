package com.wq.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wq.JSONUtil;
import com.wq.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * 测试JSON工具类使用的控制器
 */
@Controller
@RequestMapping("/JSONTest")
public class JsonController {
    // 使用这个注释 返回结果就不会被视图解析器解析
    // 直接返回一个字符串
    @ResponseBody
    @RequestMapping("/testJson1")
    public String testJson1(Model model){
        User u = new User("王强",6,"男");
        model.addAttribute("msg","");
        return u.toString();
    }

    // 使用这个注释 返回结果就不会被视图解析器解析
    // 直接返回一个字符串
    // 使用Jackson工具
    @ResponseBody
    // 原生态的SpringMVC解决json乱码问题
    @RequestMapping(value = "/testJackson", produces="application/json;charset=utf-8")
    public String testJackson(Model model) throws JsonProcessingException {
        // jacksono里面有个ObjectMaper
        ObjectMapper mapper = new ObjectMapper();
        User u = new User("王强",6,"男");
        // 使用ObjectMapper对象的writeValueAsString方法将对象转成JSON字符串
        String uStr = mapper.writeValueAsString(u);
        // return u.toString(); //User(name=??, age=6, sex=?)
        return uStr; // {"name":"??","age":6,"sex":"?"} //解决乱码后{"name":"王强","age":6,"sex":"男"}
    }

    // 使用spring.xml配置的http消息转换器和Jackson JSON转对象的工厂统一解决JSON乱码问题
    @ResponseBody
    @RequestMapping("/testJacksonCoverter") // /JSONTest/testJacksonCoverter
    public String testJacksonCoverter(Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User u = new User("王强",6,"男");
        String uStr = mapper.writeValueAsString(u);
        return uStr; // {"name":"??","age":6,"sex":"?"} // {"name":"王强","age":6,"sex":"男"}
    }

    // 将一个列表的对象都转成JSON字符串
    @ResponseBody
    @RequestMapping("/testListJSON") // /JSONTest/testJacksonCoverter
    public String testListJSON(Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User u1 = new User("王强1",6,"男");
        User u2 = new User("王强2",6,"男");
        User u3 = new User("王强3",6,"男");
        User u4 = new User("王强4",6,"男");
        List<User> userList = new ArrayList<User>();
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);
        String ulStr = mapper.writeValueAsString(userList);
        return ulStr; // 可以直接 return  new ObjectMapper().writeValueAsString(userList);
        // [{"name":"王强1","age":6,"sex":"男"},{"name":"王强2","age":6,"sex":"男"},{"name":"王强3","age":6,"sex":"男"},{"name":"王强4","age":6,"sex":"男"}]
    }

    // 返回时间戳(从1970年1月1号到现在的毫秒数)
    @ResponseBody
    @RequestMapping("/testDateJSON") // /JSONTest/testDateJSON
    public String testDateJSON(Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Date date = new Date();
        // 可以直接 return
        return new ObjectMapper().writeValueAsString(date);
    }

    // 返回人可以看懂的时间
    @ResponseBody
    @RequestMapping("/testDateFormatJSON") // /JSONTest/testDateFormatJSON
    public String testDateFormatJSON(Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 自定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateResult = sdf.format(date);

        // 使用ObjectMapper来格式化输出
        // 1.先将ObjectMapper将日期打印成时间戳的属性关闭
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 2.在传入自定义的时间格式
        mapper.setDateFormat(sdf);
        // 不用ObjectMapper的时间格式转换
        //return new ObjectMapper().writeValueAsString(dateResult);
        // 使用ObjectMapper的时间格式转换
        return mapper.writeValueAsString(date);
    }

    // 使用封装的JSON工具类来获取一个列表的对象的JSON字符串
    @ResponseBody
    @RequestMapping("/testListJSONUtil") // /JSONTest/testListJSONUtil
    public String testListJSONUtil(Model model) throws JsonProcessingException {
        User u1 = new User("王强1",6,"男");
        User u2 = new User("王强2",6,"男");
        User u3 = new User("王强3",6,"男");
        User u4 = new User("王强4",6,"男");
        List<User> userList = new ArrayList<User>();
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);
        return JSONUtil.getJsonString(userList);
    }

    // 使用封装的JSON工具类返回格式时间的JSON字符串
    @ResponseBody
    @RequestMapping("/testDateFormatJSONUtil") // /JSONTest/testDateFormatJSONUtil
    public String testDateFormatJSONUtil(Model model) throws JsonProcessingException {

       // return JSONUtil.getJsonString(new Date(), "yyyy-MM-dd HH:mm:ss");
        return JSONUtil.getJsonString(new Date());
    }

    // 使用fastjson获取JSON字符串
    @ResponseBody
    @RequestMapping("/testListFastJson") // /JSONTest/testListFastJson
    public String testListFastJson(Model model) throws JsonProcessingException {
        User u1 = new User("王强1",6,"男");
        User u2 = new User("王强2",6,"男");
        User u3 = new User("王强3",6,"男");
        User u4 = new User("王强4",6,"男");
        List<User> userList = new ArrayList<User>();
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);

        // fastjson的一些常用方法测试
        System.out.println("*******Java对象 转 JSON字符串*******");
        String str1 = JSON.toJSONString(userList);
        System.out.println("JSON.toJSONString(list)==>"+str1);
        String str2 = JSON.toJSONString(u1);
        System.out.println("JSON.toJSONString(user1)==>"+str2);

        System.out.println("\n****** JSON字符串 转 Java对象*******");
        User jp_user1=JSON.parseObject(str2,User.class);
        System.out.println("JSON.parseObject(str2,User.class)==>"+jp_user1);

        System.out.println("\n****** Java对象 转 JSON对象 ******");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(u2);
        System.out.println("(JSONObject) JSON.toJSON(user2)==>"+jsonObject1.getString("name"));

        System.out.println("\n****** JSON对象 转 Java对象 ******");
        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
        System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>"+to_java_user);


        // 一定要将新加入的包放入在项目发布里手动创建的lib文件夹里 然后重启
        return JSON.toJSONString(userList);
    }

}
