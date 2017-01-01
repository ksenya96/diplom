<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 19.11.2016
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

    var clazzes = '';
    for (var i = 1; i < 12; i++)
        if (i === ${user.clazz})
            clazzes += '<option value="' + i + ' " selected>' + i + '</option>';
        else
            clazzes += '<option value="' + i + '">' + i + '</option>';
    var dom = $('<tr id = "class">' +
            '<td>Класс: </td>' +
            '<td>' +
            '<select name="class">' +
            clazzes +
            '</select>' +
            '</td>' +
            '</tr>'
    );
    edit.append(dom);
    var dom = $('<tr id="school">' +
            '<td>Школа: <span class="star">*</span></td>' +
            '<td>' +
            '<input name="school" list="school_list" value="${user.school.name}" placeholder="Начните вводить название школы">' +
            '<datalist id="school_list">' +
            '<c:forEach items="${schools}" var="item">' +
            '<option id="${item.id}">${item.name}</option>' +
            '</c:forEach>' +
            '</datalist>' +
            '<span class="symbol"><font color="green">&#10003;</font></span>' +
            '<input type="hidden" value="${fieldsForCheckNull.add("school")}">' +
            '</td>' +
            '</tr>' +
            '<input type="hidden" class="school-hidden" name="school-hidden" value="${user.school.id}">'
    );
    edit.append(dom);

    i = 0;
    var teachers = '<table>';
    <c:forEach items="${user.teachers}" var="item">
    teachers = teachers + '<tr><td>' + (++i) + '. ${item.lastName} ${item.firstName} ${item.patronymic} ' +
            '<a href="/index?action=delete_teacher&teacher_id=${item.id}">&#10005;</a></td></tr>';
    </c:forEach>
    teachers += '</table>';

    var addTeachers = '';
    <c:forEach items="${teachers}" var="item">
    <c:if test="${! user.teachers.contains(item)}">
    addTeachers = addTeachers + '<option id=${item.id}>${item.lastName} ${item.firstName} ${item.patronymic}</option>';
    </c:if>
    </c:forEach>
    var dom = '<div>' +
            teachers +
            '<form class="formForEdit" action="/index?action=add_teacher" method="post">' +
            '<table><tr>' +
            '<td>Добавить учителя </td>' +
            '<td><input name = "teacher" list="teacher_list" placeholder = "Начните вводить фамилию">' +
            '<datalist id="teacher_list">' +
            addTeachers +
            '</datalist>' +
            '<span class="symbol">&#10007;</span><br></td>' +
            '</tr></table>' +
            '<input type="hidden" id="teacher-hidden" name="teacher-hidden">' +
            '<input class="edit_additional" type="submit" value="Добавить">' +
            '</form>' +
            '</div>';
    menu.append(dom);

    document.getElementsByClassName('add_tab')[0].innerHTML = 'Учителя';

