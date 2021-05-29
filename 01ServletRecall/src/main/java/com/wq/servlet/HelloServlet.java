package com.wq.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 编写一个Servlet(继承HttpServlet类)
 * HttpServlet(父类)最终实现了Servlet的接口
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /***
         * 在这里处理前端的请求
         */
        // 1.获取前端参数 按元素的name取参
        String reqMethod = req.getParameter("method");
        System.out.println("控制台输出");
        if("add".equals(reqMethod)){
            req.getSession().setAttribute("msg", "执行了add方法");
        }
        else if("delete".equals(reqMethod)){
            req.getSession().setAttribute("msg", "执行了delete方法");
        }else{
            req.getSession().setAttribute("msg", "method=null");
        }
        // 2.调用业务层
        /*
            3.视图转发或者重定向
            req实现转发 req.getRequestDispatcher()
            resp实现重定向 resp.sendRedirect()
            这里使用req转发
         */
        // getRequestDispatcher(要跳转到的路径) /是从web目录算起
        // 从forward中的参数可见 转发请求的含义
        req.getRequestDispatcher("/WEB-INF/jsp/returnPage.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
