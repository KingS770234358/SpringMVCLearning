SpringMVC是Spring Framework中的一部分
它是基于java实现MVC(Servlet)的轻量级WEB框架
需要的网站操作同Spring(在url里倒退回旧的版本)

为什么要学习?
1.轻量级简单易学 不需要很多依赖
2.高效, 基于请求响应的MVC框架
3.与Spring兼容性好, 无缝结合
  Spring:大杂烩/容器
  可以将SpringMVC中所有需要用到的BEAN注册到Spring中
4.约定优于配置(Maven也遇到,约定要这么做就要这么做)
5.功能强大,RESTful, 数据验证, 格式化, 本地化, 主题等
  RESTful风格:url里不使用?传参,而是用斜杠分割参数
  数据验证:上传的数据必须是邮箱手机身份证等等等等(前端也可以做,后台做更保险)
6.简介灵活

Spring的Web框架围绕DispatcherServlet(调度Servlet)设计 
请求分发,所有的请求都会经过它
点进源码分析,DispatcherServlet继承了HTTPServlet类
· 所有的请求都会经过DispatcherServlet的doService方法
· DispatcherServlet定义了一些常量
handlerMapping handlerAdapter viewResolver multipartResolver
· 可以在代码里找一下跟req resp有关的函数

#######################################
# 开始写代码:步骤
#######################################
1.创建一个Maven子模块Module
2.对这个子模块添加web的支持,右键Add Framework Support 勾选web
  可以看到项目下多了一个带蓝色点的web文件夹,这样这个子模块就可以
  被tomcat识别成一个web项目
3.在pom.xml文件中导入必要的依赖(这里由父项目完成)
4.在web/WEB_INF/web.xml中注册DispatcherServlet请求分发器
  · 定义DispatcherServlet(本质还是Servlet类)
  . 定义DispatcherServlet的URL映射
  详见web.xml文件
5.在resource/编写4需要的DispatcherServlet配置文件springmvc-servlet.xml(本质是一个spring配置文件)
  · 注入 Spring定义的 处理器映射对象
  . 注入 Spring定义的 处理器适配器对象
  · 注入 Spring定义的 视图解析器
6.java的controller包下编写用来操作事务的Controller
  (要么实现Controller接口,要么增加注解)
  [Controller是这个包下的org.springframework.web.servlet.mvc.Controller]
  返回一个ModelAndView(Model封装数据信息, View封装视图)
7.在对应的地方(WEB-INF/jsp/)下编写用于返回的视图hello.jsp
8.将上面实现的操作事务用的Controller注入/注册到SpringIOC容器中,以便使用
  这里暂且将springmvc-servlet.xml当做全局配置文件(IDEA右上角configure application context)
9.访问出现404排查步骤
  · 查看控制台输出,是不是缺少包
  · jar包存在, 就在IDEA的项目发布 WEB-INF下添加lib文件夹放入依赖包
    [project Structure->project setting->Artifacts选中要发布的项目 可以查看或设置
    WEB-INF下创建好lib文件夹后点击lib文件夹 再点击上方的 加号 就可以加入依赖包了
    项目是通过Tomcat启动的,需要放入一些包,IDEA不会在发布里自动生成依赖包]
  · 重启Tomcat即可解决

  