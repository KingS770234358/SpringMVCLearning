<%--
  Created by IntelliJ IDEA.
  User: KingS
  Date: 2020/1/28
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页面</title>
</head>
<body>
      <h1>这个是首页面</h1>
      <span>${username}</span>
<a href="${pageContext.request.contextPath}/handlerInterceptor/loginOut">注销</a>
</body>
</html>
