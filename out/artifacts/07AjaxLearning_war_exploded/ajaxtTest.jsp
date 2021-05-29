<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajaxtTest</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        //一定要加上最外面一层才能起作用
        $(function () {
            $("#btn").click(
                function(){
                    console.log("开始请求数据");
                    // /**
                    //  * 简写方式 $.post(url, param[可以省略], success);
                    //  * */
                    $.post("${pageContext.request.contextPath}/test/testJsonAjax",function (data) {
                        console.log(data);
                        // 把数据放入 下面的 tbody DOM编程
                        var tbodyContent = "";
                        // let把作用域限定在局部
                        for(let i=0;i<data.length;i++){
                            tbodyContent+="<tr>"+
                                "<td>"+data[i].name+"</td>"+
                                "<td>"+data[i].age+"</td>"+
                                "<td>"+data[i].sex+"</td>"+
                                "</tr>"
                        }
                        // 这里要加#号 表示id选择器
                        $("#content").html(tbodyContent);
                    });

                })
            });
    </script>
</head>
<body>
<input type="button" value="加载数据" id="btn"/>
<table>
    <tr>
        <td>name</td>
        <td>age</td>
        <td>sex</td>
    </tr>
    <tbody id="content">
    <%-- 数据 后台 --%>
    </tbody>
</table>
</body>
</html>
