package com.wq.config;
/***
 * 自己编写一个拦截器
 * 拦截器是AOP的应用
 * preHandle doPost afterCompletion
 * 对应于AOP的前增强 环绕增强 后增强
 * 手动重写这三个方法
 */

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    /***
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @return 如果return true就表示放行 执行下一个拦截器 类似之前的chain.
     *         如果return false 卡死
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("==========处理前==========");
        return true;
    }
    // 后面两个主要用来处理拦截日志
    /**
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @param modelAndView 数据 视图 存储结构
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("==========处理后==========");
    }

    /***
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @param ex 异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("==========清理==========");
    }
}
