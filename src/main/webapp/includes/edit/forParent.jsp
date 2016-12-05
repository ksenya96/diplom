<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 19.11.2016
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
    i = 0;
    var children = '<table>';
    var menu = $('#additional_in_menu');
    menu.empty();
    <c:forEach items="${user.pupils}" var="item">
    children = children + '<tr><td>' + (++i) + '. ${item.lastName} ${item.firstName} ${item.patronymic} ' +
            '<a href="/servlet?action=delete_child&child_id=${item.id}">&#10005;</a></td></tr>';
    </c:forEach>
    children += '</table>';

    var addChild = '';
    <c:forEach items="${pupils}" var="item">
    <c:if test="${! user.pupils.contains(item)}">
    addChild = addChild + '<option id=${item.id}>${item.lastName} ${item.firstName} ${item.patronymic}</option>';
    </c:if>
    </c:forEach>
    var dom = '<div>' +
            children +
            '<form class="forForEdit" action="/servlet?action=add_child" method="post">' +
            '<table><tr>' +
            '<td>Добавить ребенка </td>' +
            '<td><input name = "child" list="child_list" placeholder = "Начните вводить фамилию">' +
            '<datalist id="child_list">' +
            addChild +
            '</datalist>' +
            '<span class="symbol">&#10007;</span><br></td>' +
            '<input type="hidden" value="${fieldsForCheckNull.add("child-hidden")}">' +
            '</tr></table>' +
            '<input type="hidden" class="child-hidden" name="child-hidden">' +
            '<input class="edit_submit" type="submit" value="Добавить">' +
            '</form>' +
            '</div>';
    menu.append(dom);
    document.getElementsByClassName('add_tab')[0].innerHTML = 'Дети';

