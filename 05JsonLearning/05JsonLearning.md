JSON
1.背景
前后端分离时代 通过JSON进行数据交互
后端部署后端, 提供接口, 提供数据
前端独立部署, 负责渲染后端的数据
2.定义JSON(JS Object Notation)轻量级的数据交换格式
独立于编程语言的文本格式来存储、表示数据 JSON数据 {"key":"value"}

3.编写程序 完成JS对象和JSON字符串的相互转换
script标签不能单闭合/自闭合,只能双闭合
3.1.编写JS对象
var user = {
    name:"挖不去啊",
    age:15,
    sex:"男"
}
console.log(user);
// 2.调用前端自带的JSON对象的静态方法将JS对象转成JSON字符串
var jsonStr = JSON.stringify(user);
console.log(jsonStr);
// 2.调用前端自带的JSON对象的静态方法将JSON字符串转成JS对象
var jsonObj = JSON.parse(jsonStr);
console.log(jsonObj);

4.使用JSON工具包(Jackson alibaba-fastjson)
用于在java层面将对象转化成字符串
4.1 在pom.xml导入工具包的依赖
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
 <groupId>com.fasterxml.jackson.core</groupId>
 <artifactId>jackson-databind</artifactId>
 <version>2.10.1</version>
</dependency>

4.2 编写测试类[手动解决乱码问题]
使用@ResponseBody这个注释 返回结果就不会被视图解析器解析 直接返回一个字符串
[或者直接在控制器类上使用@RestController注解,则这个类下面的所有方法都不会走视图解析器,直接返回一个字符串 ]

// 使用Jackson工具
@ResponseBody
// 原生态的SpringMVC解决json乱码问题[点进RequestMapping下载源码查看produces的取值有哪些]
@RequestMapping(value = "/testJackson", produces="application/json;charset=utf-8")
public String testJackson(Model model) throws JsonProcessingException {
    // jacksono里面有个ObjectMaper
    ObjectMapper mapper = new ObjectMapper();
    User u = new User("王强",6,"男");
    // 使用ObjectMapper对象的writeValueAsString方法将对象转成JSON字符串
    String uStr = mapper.writeValueAsString(u);
    // return u.toString(); //User(name=??, age=6, sex=?)
    return uStr; // {"name":"??","age":6,"sex":"?"}
}
##################################################################
#[注意报错:因为在导入jackson包之前就导入了lib,所以jackson包不在lib里]
#一直报错,将jackson包也放进去就行了
#########################################################
4.3 以上是手动解决JSON的乱码问题,可以在[Spring.xml]中进行配置,统一解决JSON的乱码问题
<mvc:annotation-driven>
    <mvc:message-converters register-defaults="true">
        <!-- 统一解决JSON乱码问题 -->
        <!-- 字符Http消息格式转换器 传入参数utf-8 -->
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <!-- 点进源码查看即可理解 它有一个只包含一个参数的构造函数 -->
            <constructor-arg value="UTF-8"/>
        </bean>
        <!-- 使用MappingJackson2HttpMessageConverter来进行JSON字符串和对象的解析 -->
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper">
                <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                    <!-- 可以传入空值 -->
                    <property name="failOnEmptyBeans" value="false"/>
                </bean>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>

4.4做了几组实验,详见代码
主要:@RestController注解的使用,时这个类下面的方法全不去走视图解析器,直接返回一个字符串

5.使用JSON工具包fastjson
5.1pom.xml导入依赖文件
5.2在Controller中测试吧
主要有三个类
·JSONObject
·JSONArray
·JSON 代表JSONObject和JSONArray的转化
 有很多的静态方法, 也定义了默认的时间格式
JSON.toString(Object); // 不断的序列化对象,最后返回一个字符串
[一定要记得将新加入的包放入在项目发布中手动创建的lib文件夹里,然后重启]
// fastjson的一些常用方法测试
·Java对象 转 JSON字符串
·JSON字符串 转 Java对象
·Java对象 转 JSON对象
·JSON对象 转 Java对象
