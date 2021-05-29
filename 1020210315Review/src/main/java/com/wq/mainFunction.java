package com.wq;

import com.wq.controller.TestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class mainFunction {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        TestController tc = (TestController)ac.getBean("testController");

    }
}
