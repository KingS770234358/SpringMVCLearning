<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/handlerInterceptor/login" method="post">
    用户名:<input type="text" name="username"/></br>
    密　码:<input type="text" name="password"/></br>
    <input type="submit"value="提交"/></br>
</form>
</body>
</html>
