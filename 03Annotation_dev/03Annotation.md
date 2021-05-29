3.注解开发
3.1如果在创建maven项目的时候就直接将他指定成web项目
会生成web.xml,但是版本存在问题,要使用版本4的才行
最好直接右键项目 添加web的框架支持 直接就是版本4的
3.2一开始就直接在项目发布里加一个lib目录

[新的思路总结:
1.DispatcherServlet由web.xml管理
2.所有的Controller(也就是以前的Servlet)都以bean的形式注入SpringIOC容器
3.在web.xml通过读取文件配置参数的形式交给DispatcherServlet
所以===>web.xml才是网站开发的本体]

3.3配置spring
1.自动扫描指定的包,使包下的注解生效!由IOC容器统一管理
要想使用注解,一定要使用这个标签扫描包含想生效的注解的包
<context:component-scan base-package="com.wq.controller"/>
2.Spring过滤静态资源, .css .js .html .mp3 .mp4
防止他们被SpringMVC的视图解析器处理
<mvc:default-servlet-handler/>
##########################################################################
#3.支持mvc注解驱动[SpringMVC两大要素的省略]
#        在spring中一般采用@RequestMapping注释来完成映射关系
#        想要使@RequestMapping注解生效,就必须注册DefaultAnnotationHandlerMapping
#        和一个AnnotationMethodHandlerAdapter实例
#        这两个实例在类级别和方法级别进行请求处理
#        annotation-driven自动完成上述两个实例的注入
<mvc:annotation-driven/>
##########################################################################
4.注解开发也需要视图解析器[SpringMVC第三大要素]
<bean id="InternalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <property name="suffix" value=".jsp"/>
</bean>

3.4配置web
   定义DispatcherServlet
   上述的spring配置文件进行参数设置
   url和DispatcherServlet的映射关系(过滤)

3.5注解开发控制器
 * 1.加上@Controller之后就会被spring的ComponentScan自动扫描到
 * 相当于原来Spring配置中的这一步
 * <bean id="/hello" class="com.wq.controller.HelloController"/>
 *
 * 2.在类里就可以编写任意的方法了,而不像之前只能实现接口Controller的方法
 * · 封装数据 通过Model参数来封装数据
 * · 函数直接返回视图名称
 * · 当使用@RestController时, 返回的就是单纯的字符串,
 * · 不会被视图解析器解析,常用于Json数据的传输
 * ====>这样视图和数据就会自动绑定返回给HandlerAdapter,
 * 最后再给视图解析器 解析 渲染
 *
 * 3.用注解实现url和方法的映射
 *   这里的url是从配置tomcat时的application context开始算起
[这样spring-mvc.xml配置文件就再也不用改了
想要新增功能只要在@Controller的类里编写方法,完成url映射即可]
3.6编写请求结果页面
3.7配置tomcat
   · Deployment选项卡中选择正确的项目,设置合适的application context
   · server选项卡中的 On Update action和On frame deactivation
   · startup选项卡配置run debug时的CATALINA_BASE
3.8测试
