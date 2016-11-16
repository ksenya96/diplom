<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 16.11.2016
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/index/star.css">
    <meta charset="utf-8">
    <title>${user.lastName} ${user.firstName} ${user.patronymic}</title>
</head>
<body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<jsp:useBean id="fieldsForCheckNull" class="java.util.ArrayList" scope="session"/>
<h1 align="center">Учебное пособие</h1>
<h1 align="center">по основам алгоритмизации и программирования</h1>
<h1 align="center">для средней школы</h1>

<br><br><br>

${user.lastName} ${user.firstName} ${user.patronymic}
<form action="/servlet?action=exit" method="post">
    <input type="submit" value="Выйти">
</form>
<div class="menu">
    <br id="tab2"/><br id="tab3"/>
    <a href="#tab1">Анкета</a>
    <a href="#tab2" >Редактировать данные</a>
    <a href="#tab3" class="add_tab"></a>

    <div>
        ${user.lastName} ${user.firstName} ${user.patronymic} <br>
        ${user.access.value}
    </div>
    <div>
        <form id="formForEdit" action="/servlet?action=edit" method="post" onsubmit="return false;">
            <table id="edit">
                <tr>
                    <td>Фамилия: <span class="star">*</span></td>
                    <td><input type="text" name="lastName" value="${user.lastName}" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                               onkeyup="checkInput(this)" onchange="checkInput(this)">
                        <span class="symbol"><font color="green">&#10003;</font></span>
                        <input type="hidden" value="${fieldsForCheckNull.add("lastName")}">
                    </td>
                </tr>
                <tr>
                    <td>Имя: <span class="star">*</span></td>
                    <td><input type="text" name="firstName" value="${user.firstName}" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                               onchange="checkInput(this)" onkeyup="checkInput(this)">
                        <span class="symbol"><font color="green">&#10003;</font></span>
                        <input type="hidden" value="${fieldsForCheckNull.add("firstName")}">
                    </td>
                </tr>
                <tr>
                    <td>Отчество:</td>
                    <td><input type="text" name="patronymic" value="${user.patronymic}"></td>
                </tr>
                <input type="hidden" name="access" value="${user.access.value}">
            </table>
            <br>
            <input id="edit_submit" type="submit" value="Сохранить">
        </form>
    </div>
    <div id="additional_in_menu">
    </div>
</div>
<style>
    #tab2, #tab3 {position: fixed; }

    .menu > a,
    .menu #tab2:target ~ a:nth-of-type(1),
    .menu #tab3:target ~ a:nth-of-type(1),
    .menu > div { padding: 5px; border: 1px solid #aaa; }

    .menu > a { line-height: 28px; background: #fff; text-decoration: none; }

    #tab2,
    #tab3,
    .menu > div,
    .menu #tab2:target ~ div:nth-of-type(1),
    .menu #tab3:target ~ div:nth-of-type(1) {display: none; }

    .menu > div:nth-of-type(1),
    .menu #tab2:target ~ div:nth-of-type(2),
    .menu #tab3:target ~ div:nth-of-type(3) { display: block; }

    .menu > a:nth-of-type(1),
    .menu #tab2:target ~ a:nth-of-type(2),
    .menu #tab3:target ~ a:nth-of-type(3) { border-bottom: 2px solid #fff; }
</style>
<script>
    var edit = $('#edit');
    var menu = $('#additional_in_menu');
    menu.empty();
    <c:choose>
    <c:when test="${user.access.ordinal() == 0}">
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
            '<input name="school" list="school_list" value="${user.school.name}" placeholder="Начните вводить название школы" onkeypress="checkInput(this)" ' +
            'onchange="checkInput(this)" onkeyup="checkInput(this)">' +
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
    var teachers = '';
    <c:forEach items="${user.teachers}" var="item">
    teachers = teachers + (++i) + '. ${item.lastName} ${item.firstName} ${item.patronymic} ' +
            '<a href="/servlet?action=delete_teacher&teacher_id=${item.id}">&#10005;</a><br>';
    </c:forEach>
    var addTeachers = '';
    <c:forEach items="${teachers}" var="item">
    <c:if test="${! user.teachers.contains(item)}">
    addTeachers = addTeachers + '<option id=${item.id}>${item.lastName} ${item.firstName} ${item.patronymic}</option>';
    </c:if>
    </c:forEach>
    var dom = '<div>' +
            teachers +
            '<form action="/servlet?action=add_teacher" method="post">' +
            'Добавить учителя ' +
            '<input name = "teacher" list="teacher_list" placeholder = "Начните вводить фамилию">' +
            '<datalist id="teacher_list">' +
            addTeachers +
            '</datalist><br>' +
            '<input type="hidden" id="teacher-hidden" name="teacher-hidden">' +
            '<input type="submit" value="Добавить">' +
            '</form>' +
            '</div>';
    menu.append(dom);

    document.getElementsByClassName('add_tab')[0].innerHTML = 'Учителя';

    </c:when>
    <c:otherwise>
    <c:choose>
    <c:when test="${user.access.ordinal() == 1}">
    i = 0;
    var pupils = '';
    var menu = $('#additional_in_menu');
    menu.empty();
    <c:forEach items="${user.pupils}" var="item">
    pupils = pupils + (++i) + '. ${item.lastName} ${item.firstName} ${item.patronymic} ' +
            '<a href="/servlet?action=delete_pupil&pupil_id=${item.id}">&#10005;</a><br>';
    </c:forEach>
    var addPupils = '';
    <c:forEach items="${pupils}" var="item">
    <c:if test="${! user.pupils.contains(item)}">
    addPupils = addPupils + '<option id=${item.id}>${item.lastName} ${item.firstName} ${item.patronymic}</option>';
    </c:if>
    </c:forEach>
    var dom = '<div>' +
            pupils +
            '<form action="/servlet?action=add_pupil" method="post">' +
            'Добавить ученика ' +
            '<input name = "pupil" list="child_list" placeholder = "Начните вводить фамилию">' +
            '<datalist id="child_list">' +
            addPupils +
            '</datalist><br>' +
            '<input type="hidden" class="child-hidden" name="child-hidden">' +
            '<input type="submit" value="Добавить">' +
            '</form>' +
            '</div>';
    menu.append(dom);
    document.getElementsByClassName('add_tab')[0].innerHTML = 'Ученики';
    </c:when>
    <c:otherwise>
    i = 0;
    var children = '';
    var menu = $('#additional_in_menu');
    menu.empty();
    <c:forEach items="${user.pupils}" var="item">
    children = children + (++i) + '. ${item.lastName} ${item.firstName} ${item.patronymic} ' +
            '<a href="/servlet?action=delete_child&child_id=${item.id}">&#10005;</a><br>';
    </c:forEach>
    var addChild = '';
    <c:forEach items="${pupils}" var="item">
    <c:if test="${! user.pupils.contains(item)}">
    addChild = addChild + '<option id=${item.id}>${item.lastName} ${item.firstName} ${item.patronymic}</option>';
    </c:if>
    </c:forEach>
    var dom = '<div>' +
            children +
            '<form action="/servlet?action=add_child" method="post">' +
            'Добавить ребенка ' +
            '<input name = "child" list="child_list" placeholder = "Начните вводить фамилию">' +
            '<datalist id="child_list">' +
            addChild +
            '</datalist><br>' +
            '<input type="hidden" class="child-hidden" name="child-hidden">' +
            '<input type="submit" value="Добавить">' +
            '</form>' +
            '</div>';
    menu.append(dom);
    document.getElementsByClassName('add_tab')[0].innerHTML = 'Дети';
    </c:otherwise>
    </c:choose>
    </c:otherwise>
    </c:choose>


</script>
<script type="text/javascript" src="js/index/dataLists.js"></script>
<jsp:include page="includes/edit/checkEdit.jsp"/>
<script type="text/javascript" src="js/index/checkInput.js"></script>
</body>
</html>
