#################################################
# 对前端的技术要求
# HTML + css:略懂 + js(熟练掌握)
#################################################
# js:
#   · 函数:闭包
#   · Dom编程
#        document
#        ·getElementBy id name tag
#        ·create remove元素
#   · BOM编程(Browser Object Model)
#        window
##################################################
# ES6 import require 组件
##################################################

Ajax网页异步刷新工具(只刷新网页部分)
可以在F12中查看 调用的是JQuery.js
Ajax能够创造出动态性极强的web页面
1.创建web项目
1.1 右键增加框架支持
1.2 web.xml配置DispatcherServlet
1.3 配置1.2用的spring.xml文件
1.4 项目发布手动建lib包 放入依赖！！！！！
1.5 写个Controller测试(视频里用的是RestController)
1.6 配置tomcat发布项目

2.前端框架
2.1bootstrap
2.2layui(弹窗组件) 
2.3easyui
2.4elementui

3.使用iframe进行测试
3.1编写测试test.html
3.2iframe src路径
<!-- src是加载地址 testJQuery.html-->
<iframe src="test2.html" frameborder="0" style="width: 100%;height: 300px"></iframe>
这种是伪造的动态更新 只获取到了页面的xhr,并没有真正取到

4.jQuery.Ajax
JS原生XMLHttpRequest
· Ajax的核心是XMLHTTPRequest对象(XHR),为向服务器发送请求和解析服务器响应提供了接口,
能够以异步方式从服务器获取新数据
· jQuery是一个库,内部有大量JS的函数(下载uncompress的版本 原生)
· 把js css等静态资源文件夹都放到WEN-INF/statics/下
· 使用jQuery实现一些功能 

jQuery.ajax(...){
部分参数:
    url:请求地址,
    method:请求方式GET、POST,
    headers:请求头,
    data:要发送的数据,
    contentType:即将发送到服务器的内容编码类型(默认:"application/x-www-form-urlencoded; charset=UTF-8"),
    async:是否异步,
    timeout:设置请求超时时间(ms),
    beforeSend:发送请求之前执行的函数(全局),
    complete:完成之后执行的回调函数(全局),
    success:成功之后执行的回调函数(全局),
    error:失败之后执行的回调函数(全局),
    accepts:通过请求头发送给服务器,告诉服务器当前客户可接受的数据类型,
    dataType:将服务器返回的数据转成指定类型,
    "xml":将服务器返回的数据转成xml格式,
    "text":将服务器返回的数据转成普通文本格式,
    "html":将服务器返回的数据转成普通文本格式,在插入DOM时,如果包含JS标签,则会尝试去执行,
    "script":尝试将返回值当作JavaScript去执行,然后再将服务器端返回的内容转换成普通文本格式,
    "json":将服务器返回的数据转成相应的JavaScript对象,
    "jsonp": JSONP 格式使用 JSONP 形式调用函数时,如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名,以执行回调函数,    
}

index.jsp中编写聚焦事件测试
=====>更改资源路径映射需要rebuild
=====>向ajax回调函数(success error)传参顺序(data,status,...)
status 200成功 300重定向 400请求错误 500服务器错误

5.Ajax结合Json传递数据(使用第二个测试页面ajaxTest.jsp)
[$=jquery $.ajax=$.post]
file->settings->javascript->es6

6.用户登录实时监测的例子login.jsp
[常用事件:
onblur onfocusout onmouseleave ]

7.有精力可以拓展下 ajax 跨域请求(不重要)
