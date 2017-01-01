<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 31.12.2016
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
${theory.content}
<c:forEach var="item" items="${tasks}">
    <a href="/task?task_id=${item.id}">${item.title}</a>
</c:forEach>
