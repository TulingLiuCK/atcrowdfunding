<%--
  Created by IntelliJ IDEA.
  User: 刘崇康
  Date: 2022/11/22
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $("#btn1").click(function () {
            $.ajax({
                "url": "send/array/one.json",
                "type": "post",
                "data": {
                    "array": [5, 8, 12]
                },
                "dataType": "text",
                "success": function (response) {
                    alert(response)
                },
                "error": function (response) {
                    alert(response)
                }
            });
        })
        $("#btn2").click(function () {
            $.ajax({
                "url": "send/array/two.json",
                "type": "post",
                "data": {
                    "array[0]": 5,
                    "array[1]": 6,
                    "array[2]": 7,
                },
                "dataType": "text",
                "success": function (response) {
                    alert(response)
                },
                "error": function (response) {
                    alert(response)
                }
            });
        })
        $("#btn5").click(function () {
           layer.msg("layerde tanchaung ")
        })
    })
</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="test/ssm.html">测试SSM整合环境</a>
<br>
<button id="btn1">Sen [5,8,12] One</button>

<br>
<button id="btn2">Sen [5,8,12] Two</button>

<button id="btn5">点我弹窗</button>>
</body>
</html>
