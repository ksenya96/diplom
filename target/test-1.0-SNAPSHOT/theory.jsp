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

<c:if test="${user != null}">
    <c:choose>
        <%--Показывать задания, если ученик подписан или пользователь не является учеником--%>
        <c:when test="${user.access == 'PUPIL' && user.themes.contains(theory.theme) || user.access != 'PUPIL'}">
            <c:forEach var="item" items="${tasks}">
                <a href="/task?task_id=${item.id}">${item.title}</a>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <a href="/theory?action=subscribe">Подписаться на задания</a>
        </c:otherwise>
    </c:choose>
</c:if>


