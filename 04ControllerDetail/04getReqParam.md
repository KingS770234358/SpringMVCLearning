05.接收请求参数 以及 数据回显
这节讲的就是传统的传参方式(而不是RestFul风格的)
localhost:8080/子项目名/控制器名?param1=value1&param2=value2&...
@RequestMapping(/控制器名)
public String test(参数类型 参数名){
}
1.当参数名==param1时,可以直接获取
2.当参数名!=param1是,需要使用@RequestParam("param1")注解
3.当参数为对象时如何处理

[编写POJO User使用lombok不要忘了把lombok依赖放入项目发布里手动创建的lib包里]

1.不使用@RequestParam注解的情况
/***
* 当前端传来的URL参数和形参名一样, 可以直接接受传入
* 当前端传来的URL参数和形参名不一样,则取值为空
*/

2.使用@RequestParam注解的情况
/***
* 当前端url中传入的参数与方法的形参名不一样的时候需要使用注解:@RequestParam("username") 
* 前端要传这个字段只能以username传,[如果前端不按username来传这个参数会报 400 错误]
* 注意:不论如何都加上就对了,可以标识是前端要用的(传来的)参数
*/

3.当前端url参数传入一个对象的时候
/***
 * 测试前端传入一个对象的情况
 * User 类 id name age
 * 这种情况下,前端url中传入的参数名要和User类每个字段都一一对应,都要一样
 * 如果有哪个参数名和它对应的User里面的属性名不一致,则这个值为null或0
 * 只要url中传参数名和属性名一样,url中参数的位置可以随意!
 */ 

4.封装Model数据常用的数据结构
ModelAndView(实现Controller接口要使用,方式比较老)
Model是一个接口
ModelMap继承LinkHashMap实现Model
所以ModelMap拥有LinkHashMap的全部功能,比Model强大
Model是精简版,大部分都使用Model