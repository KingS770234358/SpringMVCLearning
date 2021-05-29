05.SpringMVC结果跳转方式
·通过实现Controller接口实现的ModelAndView
 设置ModelAndView对象,根据view的名称 和视图解析器跳转到指定页面
 页面:视图解析器前缀+viewName+视图解析器后缀 
·通过注解方式实现的Model和return

[· 通过ServletAPI来实现,不需要视图解析器]
1.通过HttpServletResponse进行输出
  rsp.getWritter().println(""sss");
2.通过HttpServletResponse实现重定向
  rsp.sendRedirect("/index.jsp");
3.通过HttpServletRequest实现转发
  req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forword(req,rsp);
HttpServletResponse/HttpServletRequest本质上都是Servlet(req,rsp)
DispatcherServlet里面有个doService(req,rsp)方法
测试
[可以先将视图解析器注释掉,测试结果更明显]
servletAPI方式与视图解析器方式可以同时存在.
但是return的时候要说明redirect:还是forward:
否则会被视图解析器拼接成不存在的资源全限定路径



[XXX.sout可以实现快速打印XXX
XXX.var可以实现快速声明变量赋值]

总结下@RequestMapping的方法可以传入哪些参数
HttpServletRequest request, HttpServletResponse response Model
以及PathVariable
