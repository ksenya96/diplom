<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 14.11.2016
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    i = 0;
    var pupils = '';
    var menu = $('.menu');
    <c:forEach items="${user.pupils}" var="item">
    pupils = pupils + (++i) + '. ${item.lastName} ${item.firstName} ${item.patronymic} ' +
            '<a href="/servlet?action=delete_pupil&pupil_id=${item.id}">&#10005;</a><br>';
    </c:forEach>
    menu.append(pupils);
    document.getElementsByClassName('add_tab')[0].innerHTML = 'Ученики';
</script>
