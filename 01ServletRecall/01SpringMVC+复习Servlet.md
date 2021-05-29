SpringMVC
官网:....
ssm:SpringMVC + Spring + Mybatis
[MVC三层架构], SpringMVC属于Spring
javaSE
javaWeb
框架:研究官方文档, 自学能力, 笔记能力, 项目能力

还有:SpringMVC+Vue+SpringBoot+SpringCloud+Linux
博客园 狂神说 新博客:狂神说,秦疆Java世界

##################################
# Spring:IOC 和 AOP
# SpringMVC:SpringMVC的执行流程
# SSM框架整合 = javaWeb做项目
##################################

1.2 MVC架构(SpringMVC就是对Servlet的封装)
model(Dao,Service)模型层 view(jsp)视图层 controller(最原生Servlet)控制层
常见的服务器端MVC框架:structs/Struct2/SpringMVC/ASP.NET MVC/Zend Framework/JSF
常见的前端MVC框架:[vue/angularjs/react]/backbone/Zend Framework/JSF
演化出的一些别的框架MVP MVVM 等等, 后面还要对比MVVM(M V ViewModel-控制层-双向绑定)框架

dao:管理数据
service:管理业务
servlet:接收前端的数据(转发url不变/重定向)
jsp/html

前端 数据传输 实体类
实体类:用户名 密码 生日 爱好 ... 20个属性
前端:用户名 密码(登录的时候只需要用户名、密码)
pojo:User [细分]成vo dto 用于视图层和数据层的传输
vo:Uservo (只写两个字段)
dto:Userdto
最基础实现就是JSP + Servlet + javabean的实现

面试:项目的架构是设计好的还是[演进的] Alibaba
php->java->王坚去IOE MySQL->AliSQL AliRedis
All in one--->微服务 

Model 1
      ---1.request------>2.jsp请求参数-----3.调用业务逻辑方法----->
客户端                         JSP                           业务逻辑
      <--5.response----4.数据返回后渲染页面<----------------------  

Model 2
      ----------1.request------>     ---------2.Servlet分配请求------> 
客户端                         Servlet                           业务逻辑
      |                     转发/ |   <-------3.数据返回后渲染页面------- 
      |                     重定向|      
      <---------5.response-------JSP
1. 用户发起请求
2. Servlet处理请求,并调用业务逻辑方法
3. 业务处理完毕,返回更新后的数据给Servlet
4. Servlet转向到JSP,有JSP来渲染页面
5. 响应给前端跟新后的页面
Controller控制器
· 取得表单数据
· 调用业务逻辑
· 转向指定的页面
Model模型
· 业务逻辑
· 保存数据的状态
VIEW视图
· 显示页面

1.3 回顾Servlet
开始创建项目
· 创建maven项目
· 删除src文件夹,方便构建子项目
· 导入必要的包
· 创建子项目
(创建一个普通的maven项目,不要勾选webapp,勾选的话要改web.xml里面的版本)
· 右键子项目 add Framework Support--->勾选web application 、版本、Create web.xml  
目录上会出现一个与src同级的[带有蓝点的]web文件夹,说明他是一个web项目
如果不带有蓝点 无法fix tomcat
· 最好在子项目pom.xml中也导入需要用的依赖
· 编写Servlet用来处理用户请求
  ---new一个class继承HttpServlet;
  ---重写doGet doPost方法(可以删除override里面的super)
     (两者只是提交方式不一样,在doPost里引用doGet实现doGet的复用)
     在doGet里面处理用户的请求,从req对象中获取请求参数
· 编写转发/重定向使用的页面(重要页面放在WEB-INF下面,公共页面放在web下面)
 
· 在web.xml中要配置Servlet
  ---配置servlet serlet路由映射 session 欢迎页面等
· 编写发出请求的页面 为了方便直接放在 web文件夹下
  (为了方便 也可以直接通过web.xml配置的路由映射直接访问
  想要测试的Servlet类)

· 配置tomcat
(IDEA右上角绿色锤子build 旁边的Add Configuration配置)
· 通过tomcat扫描web项目工程
  如果项目下有web工程 直接点击右下角红色的fix即可扫描到
  然后在application context设置路由
  (此处设置的路由的使用 localhost:8080/[application context]/[Servlet Url Mapping]?param=value)
  #############################################################################################
  # 点击Server选项卡, 找到on frame deactivation里选择 Update classes and resources [###热部署###]
  #############################################################################################
· 启动tomcat(确保使用的端口8080没被占用)
· 浏览器访问localhost:8080/01ServletRecall/hello?method=value
  [这里一定要在hello后面带上参数, 因为Servlet里取获取了这个参数, 视频里的写法容易报异常]
步骤总结:
· Deployment选项卡中选择正确的项目,设置合适的application context
· server选项卡中的 On Update action和On frame deactivation
· startup选项卡配置run debug时的CATALINA_BASE
 
#################################################################################################
# 三个问题: tomcat控制台红字乱码:在D:\Tomcat9\apache-tomcat-9.0.30\conf\logging.properties中
#          把java.util.logging.ConsoleHandler.encoding = UTF-8修改成GBK
#          热部署问题
#          CATALINA_BASE配置:run 和 debug模式要分别配置 在Startup选项卡里新增环境变量CATALINA_BASE
#################################################################################################

最基础实现就是JSP + Servlet + javabean的实现
通过url传参,在Servlet中取出[一个个的]参数 进行判断 然后做响应的操作 
SpringMVC可以简化操作
[通过以上实现 总结下 SpringMVC要做哪些事]
1.将url映射到java类或java类的方法(主要是Servlet类?)
2.封装用户提交的数据
3.处理请求, 调用相关的业务处理, 封装响应得到的数据
4.将响应得到的数据进行渲染 jsp/html等view(表示层)数据