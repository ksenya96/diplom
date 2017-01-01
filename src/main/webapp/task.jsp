<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 23.08.2016
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<canvas height="320" width="480" id="example">Обновите браузер</canvas>
<script>

    var example = document.getElementById("example"),
            ctx = example.getContext('2d');
    ctx.clearRect(0, 0, example.width, example.height);
</script>

</body>
</html>
