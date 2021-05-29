SpringMVC拦截器HandlerInterceptor
1.拦截器简介
拦截器相当于Servlet开发中的Filter过滤器(比如现在定义的乱码过滤器)
拦截器是AOP的应用,横切进去的
拦截器是SpringMVC框架的,只有使用了SpringMVC框架的工程才能使用;
拦截器只会拦截访问的控制器方法,不会拦截    静态资源jsp/html/css/image/js

2.拦截器的定义
2.1新建项目,导入包,添加web支持
2.2配置web.xml和spring-mvc.xml
2.3编写拦截器(实现HandlerInterceptor接口即可)

3.拦截器
3.1 拦截器算是一种配置 写一个com.wq.config
* 拦截器是AOP的应用 
* preHandle doPost afterCompletion
* 对应于AOP的前增强 环绕增强 后增强
* 实现HandlerInterceptor接口即可 手动重写这三个方法 
* 第一个就可以返回是否拦截true放行 false拦截 后面两个主要用来处理拦截日志
[3.2 因为拦截器是springmvc的东西,一定要在spring-mvc.xml里面注册!!!!(这里就是applicationContext.xml文件)]
3.3 实现主页的保护LoginInterceptor login.jsp main.jsp
[过滤主要是通过session里的数据和URI判断是否过滤
1.session.getAttribute("username")
2.request.getRequestURI().contains("ogin")]

[总结下@RequestMapping的方法可以传入哪些参数
HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session  
以及PathVariable]