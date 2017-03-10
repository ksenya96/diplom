<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 29.12.2016
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2 align="center">${clazz} класс</h2>
<c:if test="${themes.isEmpty()}">
    <p align="center">Тем нет</p>
</c:if>

    <ol class="rounded">
    <c:forEach var="item" items="${themes}">

            <li><a href="/theory?theme_id=${item.id}">${item.title}</a></li>

    </c:forEach>
    </ol>

