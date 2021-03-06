<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 16.11.2016
  Time: 19:09
  To change this template use File | Settings | File Templates.
  Залогиненный пользователь
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>

<html>
<head>
    <link rel="shortcut icon" href="/images/robot.png" type="image/png" />
    <link rel="stylesheet" type="text/css" href="css/index/star.css">
    <meta charset="utf-8">
    <title>${user.lastName} ${user.firstName} ${user.patronymic}</title>
    <link rel="stylesheet" type="text/css" href="css/blue/style.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.metadata.js"></script>
    <script type="text/javascript" src="js/jquery.tablesorter.js"></script>
</head>
<body>
<jsp:useBean id="fieldsForCheckNull" class="java.util.ArrayList" scope="session"/>

<header>
<div align="right">
    <a href="/index"><p style="display:inline-block;">${user.lastName} ${user.firstName} ${user.patronymic}</p></a>
    <form style="display:inline-block;" action="/index?action=exit" method="post">
        <input type="submit" value="Выйти">
    </form>
</div>

    <div align="center">
        <div style="display: inline-block; float: left"><img src="/images/bell.png" width="150" height="150"></div>
        <div style="display: inline-block; vertical-align: middle; align-content: center;" ><h1><i>
            Основы алгоритмизации и программирования</i></h1>
            Учебное пособие для средней школы
        </div>
    </div>
    <hr>
</header>


<table width="100%">
    <tr>
        <td id="nav" valign="top">
            <nav>
            </nav>
        </td>
        <td valign="top" align="center">
            <main>
                <c:if test="${content == 'main'}">
                    <div class="menu" align="center">
                        <br id="tab2"/><br id="tab3"/>
                        <a href="#tab1">О пользователе</a>
                        <a href="#tab2">Редактировать данные</a>
                        <a href="#tab3" class="add_tab"></a>

                        <div>
                                ${user.lastName} ${user.firstName} ${user.patronymic} <br>
                                ${user.access.value}
                            <c:if test="${user.access.ordinal() == 0}">
                                <br>
                                ${user.clazz} класс<br>
                                ${user.school.name}
                            </c:if>
                        </div>
                        <div>
                            <form class="formForEdit" action="/index?action=edit" method="post" onsubmit="return false;">
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
                                <input class="edit_submit" type="submit" value="Сохранить">
                            </form>
                        </div>
                        <div id="additional_in_menu">
                        </div>
                    </div>
                </c:if>
                <c:if test="${content == 'theory'}">
                    <jsp:include page="theory.jsp"/>
                </c:if>
                <c:if test="${content == 'themes'}">
                    <jsp:include page="includes/user/themes.jsp"/>
                </c:if>
                <c:if test="${content == 'task'}">
                    <jsp:include page="task.jsp"/>
                </c:if>

            </main>
        </td>
    </tr>
    </table>



<script>
    $('nav').empty();
    for (var i = 1; i <= 11; i++) {
        $('nav').append('<a class="menu_item" href="/themes?action=class&class=' + i + '">' + i + ' класс</a>');
    }
</script>

<script>
    var edit = $('#edit');
    var menu = $('#additional_in_menu');
    menu.empty();
    <c:choose>
        <c:when test="${user.access.ordinal() == 0}">
            <jsp:include page="includes/edit/forPupil.jsp"/>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${user.access.ordinal() == 1}">
                    <jsp:include page="includes/edit/forTeacher.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="includes/edit/forParent.jsp"/>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>


</script>
<script type="text/javascript" src="js/index/dataLists.js"></script>
<script type="text/javascript" src="js/index/checkInput.js"></script>
<jsp:include page="includes/edit/checkEdit.jsp"/>
<jsp:include page="includes/edit/checkAdditional.jsp"/>
<c:if test="${clazz != null}">
    <script>
        var links = $('nav').children();
        for (var i = 0; i < links.length; i++) {
            if (links[i].innerHTML === '${clazz} класс') {
                $(links[i]).addClass('active');
            }
            else {
                $(links[i]).removeClass('active');
            }
        }
    </script>
</c:if>
<script>
    var width = $('.menu_item').css('width');
    $('#nav').attr('width', width);
</script>
</body>
</html>
