<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发出请求的页面</title>
</head>
<body>
<!-- action中写上想要访问的Servlet的url映射(在web.xml中配置的) -->
<form action="/hello" method="post">
    <input type="text" name="method"/>
    <input type="submit"/>
</form>
</body>
</html>
