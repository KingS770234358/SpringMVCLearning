文件的上传与下载
1.引入上传和下载文件所需要pom.xml依赖
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.4</version>
</dependency>
[记得在项目发布里手动创建的lib里也加入,否则报错 class not found]
2.说明:springMVC可以很好的支持文件上传,但上下文中默认没有装配MultipartResolver;
因此默认情况下无法处理文件的上传，
如果想使用Spring文件上传功能,则要在上下文中配置MultipartResolver

3.对前端的要求
必须将form的提交method设置为POST,并将enctype设置为multipart/form-data
这样浏览器才会把用户选择的文件以[二进制数据]发送给服务器
·对enctype属性的详细说明:
·application/x-www=form-urlencoded:默认方式,只处理表单域中的value属性值,
采用这种编码方式的表单会将表单域中的值处理成url编码方式
·multipart/form-data:这种编码方式会以二进制的方式处理表单数据
这种编码方式会把文件域指定的文件内容也封装到请求参数中,不会对字符编码
·text/plain:除了把空格转换成"+"以外,其他字符都不做编码处理,这种方式适合直接通过表单发送邮件

4.编写前端表单index.jsp
    <form action="${pageContext.request.contextPath}/upload" method="post"
          enctype="multipart/form-data">
      <input type="file" name="file"/>
      <input type="submit" name="上传"/>
    </form>
5.在spring.xml文件中配置文件上传的支持MultipartResolver
    <!-- 配置文件上传的支持MultipartResolver -->
    <!-- 这个id必须写这个 有其他地方调用到 -->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!-- 请求的编码格式,必须和jsp的pageEncoding属性一致,
        以便正确读取表单的内容 默认为ISO-8859-1 -->
        <property name="defaultEncoding" value="utf-8"/>
        <!-- 上传文件大小上限(单位:字节) -->
        <property name="maxUploadSize" value="10485760"/>
        <property name="maxInMemorySize" value="40960"/>
    </bean>
6.对后端的要求
将name=file  控件得到的文件封装成CommonsMultipartFile 对象
//批量上传CommonsMultipartFile则为数组即可
public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {

7.上传测试
上传后的文件放在maven的out目录里
out/artifacts/09FileDownUpLoad_war_exploded/upload/作用域和生命周期.PNG
[                                       web/upload/作用域和生命周期.PNG]
String path = request.getServletContext().getRealPath("/upload");
File realPath = new File(path)
file.transferTo(new File(realPath +"/"+ file.getOriginalFilename()));

8.文件下载
步骤
·设置 response 响应头
·读取文件 -- InputStream
·写出文件 -- OutputStream
·执行操作
·关闭流 （先开后关）
[为了安全考虑,一般会把用户需要下载的文件放在WEB-INF目录下,这样改变了目录结构,需要rebuild项目]