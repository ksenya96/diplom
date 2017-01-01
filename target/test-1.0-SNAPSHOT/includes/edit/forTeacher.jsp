<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 14.11.2016
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    var i = 0;
    var pupils = '<table>';
    var menu = $('#additional_in_menu');
    menu.empty();
    <c:forEach items="${user.pupils}" var="item">
        pupils = pupils + '<tr><td>' + (++i) + '. ${item.lastName} ${item.firstName} ${item.patronymic} ' +
            '<a href="/index?action=delete_pupil&pupil_id=${item.id}">&#10005;</a></td></tr>';
    </c:forEach>
    pupils += '</table>';

    var addPupils = '';
    <c:forEach items="${pupils}" var="item">
        <c:if test="${! user.pupils.contains(item)}">
            addPupils = addPupils + '<option id=${item.id}>${item.lastName} ${item.firstName} ${item.patronymic}</option>';
        </c:if>
    </c:forEach>
    var dom = '<div>' +
            pupils +
            '<form class="formForEdit" action="/index?action=add_pupil" method="post">' +
            '<table><tr>' +
            '<td>Добавить ученика </td>' +
            '<td><input name = "pupil" list="child_list" placeholder = "Начните вводить фамилию">' +
            '<datalist id="child_list">' +
            addPupils +
            '</datalist>' +
            '<span class="symbol">&#10007;</span><br></td>' +
            '</tr></table>' +
            '<input type="hidden" class="child-hidden" name="child-hidden">' +
            '<input class="edit_additional" type="submit" value="Добавить">' +
            '</form>' +
            '</div>';
    menu.append(dom);
    document.getElementsByClassName('add_tab')[0].innerHTML = 'Ученики';
