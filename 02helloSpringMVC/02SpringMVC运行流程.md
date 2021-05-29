SpringMVC运行流程图(结合这节的图片02运行流程图2.0.png理解)
实线是SpringMVC实现的 虚线是要自己实现的
1.DispatcherServlet表示前置控制器(分配转发请求),是整个SpringMVC的控制中心
用户发出请求,DispatcherServlet接收、拦截请求
· 假设请求url: http://localhost:8080/02helloSpringMVC/hello
· 拆分成三个部分
  http://localhost:8080服务器域名(ip:端口号) 
  02helloSpringMVC表示部署在服务器上的web站点
  hello表示要访问的控制器(就是具体的操作业务)
2&3.DispatcherServlet调用它的[①HandlerMapping]根据浏览器里输入的url请求查找Handler
  可以参考02DispatcherServlet的角色.png,就是从多个Handler中找到一个和url匹配的
  也就是从springmvc-servlet.xml配置中多个的Controller中选出id匹配的[先找到/hello]
    <!-- handler处理器 -->
    <bean id="/hello" class="com.wq.controller.HelloController"/>
4.上一步返回一个[①HandlerExecution]给DispatchperServlet,里面包含了匹配到的Controller信息[/hello返回给DispatcherServlet]
5&6.DispatcherServlet调用它的[②HandlerAdapter],根据上一步得到的[①HandlerExecution]里的
  Controller信息去所有实现了Controller接口的类中找对应的Controller类com.wq.controller.HelloController[找到对应的控制器类]

67之间:Controller执行业务层方法,得到数据,封装成Model;
       确定要将数据展示在哪个视图,就把那个视图分装起来(主要是确定视图名[没有前后缀])
       得到ModelAndView对象

7.Controller将ModelAndView对象返回给DispatcherServlet它的[②HandlerAdapter]
8.[②HandlerAdapter]将得到的ModelAndView对象返回给DispatcherServlet
9.DispatcherServlet将得到的ModelAndView对象传给它的[③视图解析器];
10.[③视图解析器]根据ModelAndView对象里存的视图(View)名,解析出完整的视图路径,取得视图;
   将ModelAndView对象里存的数据(Model)渲染到取得的视图上后返回给DispatcherServlet
11&12.DispatcherServlet调用[③视图解析器]的解析结果呈现给用户