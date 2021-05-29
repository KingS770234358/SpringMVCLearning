<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Ajax测试</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <!-- js是一个随便的语言 -->
    <script>
      function a() {
        $.post({
          url:"${pageContext.request.contextPath}/test/a11",
          data:{"name":$("#username").val()},
          success:function (data, status) {
            console.log("data="+data);
            console.log("status="+status);
          },
          error:function (data,status) {
            console.log("status="+status);
          }
        })
      }
    </script>
  </head>
  <body>
  <%-- onblur 失去焦点-点进来又点出去 的时候,发送一个请求(携带信息)到后台 --%>
  用户名:<input type="text" id="username" onblur="a()"/>
  </body>
</html>
