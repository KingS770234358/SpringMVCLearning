<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/upload" method="post"
          enctype="multipart/form-data">
      <input type="file" name="file"/>
      <input type="submit" name="上传"/>
    </form>
    下载文件的测试链接:
    <a href="${pageContext.request.contextPath}/download">点击下载</a>
  </body>
</html>
