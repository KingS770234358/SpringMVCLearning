Annotation开发SpringMVC的几个问题
1.为什么加上Controller标签就有用了
2.RequestMapping还有哪些写法
3.Model参数该如何封装
4.return结果怎么跳转的(有哪些方式)

一、Controller和RestFul风格
1.控制器Controller
· 提供访问应用程序的行为(实现接口定义或者注解定义)
· 解析用户请求,封装数据
· 一个控制器可以包含多个方法
· SpringMVC对Controller的配置有很多种
[小技巧:建立lib文件夹导入依赖包的时候点击第一个,
再按住shift点击最后一个可以完成全选]

· 双击shift 搜索controller函数式接口 下载源码
[新的发现:web.xml文件中对于DispatcherServlet的映射这里最好写/
<servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
以后再详细了解吧
可能是:这里的url-mapping过滤的是application context后面一串的东西
eg:localhost:8080/04ControllerDetail/hello,过滤的是/hello这一串]

################################################################
# [新的操作:使用实现Controller接口方式开发的时候,在spring配置文件中
# 不配置HandlerMapping 和 HandlerAdapter也可以实现成功访问]
# 之前讲课是为了弄清楚SpringMVC的原理
################################################################

[新的bug:进入控制器,但是返回页面为空====>一定要在控制器里return mv!]

2.实现controller接口方式的缺点
· 一个url对应一个一个控制器实现类,
· 一个控制器实现类中只有一个方法
===>推荐使用注解的方式实现

3.关于@Controller的解释
Spring中四个等效的注解 都代表一个组件
@Component    组件
@Service      service层
@Controller   Controller层
@Repository   Dao层
[狂神的博客:blog.kuangstudy.com]

4.关于requestMapping的解释
可以放在类上,也可以放在类里的方法上
访问类里的方法时,需要上面两个url拼接
一种使用场景:
可以定义一个@RequestMapping("admin")类的控制器,
所有访问页面admin的url就都必须先带上admin
[狂神个人建议:都在类上加RequestMapping,
直接在方法上加RequestMapping("/admin/xxx)]

##################
# 5.RestFul风格
##################
背景:
以前的传参方式:localhost:8080/xxx?method=add&param2=value2&...
RestFul风格:localhost:8080/xxx/add/value2/...
定义:就是一种资源定位及资源操作的风格,不是标准 也不是协议,
     基于这种风格设计的软件更加简洁,有层次,[更易于实现缓存机制]
     ·资源:互联网上的任何事物
     ·资源操作:使用POST/DELETE/PUT/GET 使用不同的方法对资源进行操作
               分别对一个增删改查
传统的(struct)资源操作方式:使用不同的参数实现不同的效果,方法单一,POST和GET
·查询 GET localhost:8080/item/queryItem.action?id=1
·新增 POST localhost:8080/item/saveItem.action
·更新 POST localhost:8080/item/updateItem.action
·删除 GET/POST localhost:8080/item/deleteItem.action?id=1
RestFul风格:请求方式也作为参数之一(请求方式不同 效果.功能不同)[隐藏参数名!!!]
·查询 GET localhost:8080/item/1 
·新增 POST localhost:8080/item
·更新 PUT localhost:8080/item
·删除 DELETE localhost:8080/item/1

5.2 RestFul风格代码实现
[最原始的风格]http://localhost:8080/04ControllerDetail/add?a=1&b=2
// 请求URL中的参数名一定要和对应的方法中形参名一模一样,否则500错误
// 不带参数的URL也会 500 错误
@RequestMapping("/add")
public String test1(int a, int b, Model model){
    int res = a+b;
    model.addAttribute("msg","结果为:"+res);
    return "hello";
}
[RestFul风格基本使用1]RestFul风格:http://localhost:8080/04ControllerDetail/add/1/2
@RequestMapping(name="MappingName", value = "/add/{a}/{b}", method = RequestMethod.GET)
public String test2(@PathVariable int a, @PathVariable int b, Model model){
    int res = a+b;
    model.addAttribute("msg","结果为:"+res);
    return "hello";
}
注意点1.value = path 匹配url中的参数,这里方法使用的@PathVariable形参变量名一定要和value/path花括号中填写的一直 否则500
注意点2.实际url请求中的参数类型一定要和方法定义的形参类型一致 否则400错误
注意点3.注解RequestMapping的name 可能就是给这个映射起个名字而已 与url路径无关
注意点4.method定义方法处理的提交类型 RequestMethod.GET/POST/PUT/DELETE等等 点@RequestMapping查看源码,他是一个枚举类
// public enum RequestMethod {
    GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS,TRACE;
    private RequestMethod() {
    }
}
[RestFul风格基本使用2]
更为简化的写法[如下只有以GET方式提交的add请求才会被该方法处理]
@PostMapping @DeleteMapping @PutMapping @GetMapping @PatchMapping
@GetMapping(value = "/add/{a}/{b}")
public String test2(@PathVariable int a, @PathVariable int b, Model model){
    int res = a+b;
    model.addAttribute("msg","结果为:"+res);
    return "hello";
}
[RestFul风格基本使用3]
当存在两个处理的提交方式相同 url /add部分也相同的时候 即使方法传入的形参类型不同 redeploy也会失败
====>SpringMVC[不会通过形参的数据类型来判断使用哪个方法]
@GetMapping(value = "/add/{a}/{b}")
public String test3(@PathVariable int a, @PathVariable String b,Model model){
    int res = a;
    model.addAttribute("msg","结果为:"+res);
    return "hello";
}
[RestFul风格基本使用4]
当存在两个处理的提交方式相同 url /add部分也相同的时候 [允许形参个数不一样的方法存在] redeploy成功
@GetMapping(value = "/add/{a}/{b}/{c}")
public String test4(@PathVariable int a, @PathVariable int b,@PathVariable String c,Model model){
    String res = a+b+c;
    model.addAttribute("msg","结果为:"+res);
    return "hello";
}
[RestFul风格基本使用5]
在web下(而不是WEB-INF下)写一个能直接访问的表单测试POST提交方式 Mapping的value相同
@PostMapping(value = "/add/{a}/{b}")
public String test5(@PathVariable int a, @PathVariable int b,Model model){
    // POST提交和GET提交结果明显不同
    int res = a+b+100000;
    model.addAttribute("msg","结果为:"+res);
    return "hello";
}
注意表单action的写法:action="/04ControllerDetail/add/2/2 要加上/04ControllerDetail
[RestFul风格总结]
·路径变得更加简洁
·获得参数更加方便、框架可以自动进行类型转换
·通过路径变量(@PathVariabel)类型可以约束来访的url参数
·=====>安全,隐藏参数,防止后台代码的使用推演