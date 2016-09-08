<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 21.08.2016
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
${content}
<c:forEach items="${tasks}" var="item">
    <div>
        <a href="/servlet?task=${item.id}">${item.title}</a><br>
    </div>
</c:forEach>
</body>
</html>
