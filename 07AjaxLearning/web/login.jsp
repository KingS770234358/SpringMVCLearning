<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login Test Ajax</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function nameCheck(){
                console.log("开始请求数据");
                // /**
                //  * 简写方式 $.post(url, param[可以省略], success);
                //  * */ 这里一定要传数据啊
                $.post("${pageContext.request.contextPath}/test/checkUser",
                    {"name":$("#name").val(),"pwd":$("#pwd").val()},
                    function(data) {
                    // 这里加一些渲染
                    if(data[0]==='ok'){
                        $("#userInfo").css("color","green");
                    }
                    else{
                        $("#userInfo").css("color","red");
                    }
                    $("#userInfo").html(data[0]);
                });
        }
        function nameCheck2(){``
            console.log("开始请求数据");
            $.post("${pageContext.request.contextPath}/test/checkUser",
                {"name":$("#name").val(),"pwd":$("#pwd").val()},
                function (data) {
                    if(data[1]==='ok'){
                        $("#pwdInfo").css("color","green");
                    }
                    else{
                        $("#pwdInfo").css("color","red");
                    }
                    $("#pwdInfo").html(data[1]);
            });
        }
    </script>
</head>
<body>
<p>
    用户名:<input type="text" id="name" onfocusout="nameCheck()"/>
    <span id="userInfo"></span>
</p>
<p>
    密　码:<input type="text" id="pwd" onfocusout="nameCheck2()"/>
    <span id="pwdInfo"></span>
</p>
</body>
</html>
