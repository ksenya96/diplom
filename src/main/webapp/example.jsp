<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 13.08.2016
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" session="true" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/index/star.css">
    <meta charset="utf-8">
    <title>Учимся играя</title>
</head>
<body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<jsp:useBean id="fieldsForCheckNull" class="java.util.HashMap" scope="session"/>

<header>


    <div align="right">
        <dialog id="enter">
            <h2 align="left" style="display:inline-block; float: left;">Вход в систему</h2>
            <button id="exit_enter" style="display:inline-block;">&#10005;</button>
            <form action="/index?action=login" method="post" id="formForEnter" onsubmit="return false;">
                <table>
                    <tr>
                        <td>Логин:</td>
                        <td>
                            <input type="text" name="login" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                                   onkeyup="checkInput(this)" onchange="checkInput(this)">
                            <span class="symbol">&#10007;</span>
                        </td>
                    </tr>
                    <tr>
                        <td>Пароль:</td>
                        <td>
                            <input type="password" name="password" onkeypress="checkInput(this)"
                                   onfocus="checkInput(this)"
                                   onkeyup="checkInput(this)" onchange="checkInput(this)">
                            <span class="symbol">&#10007;</span>
                        </td>
                    </tr>
                </table>
                <div align="left">
                    <input type="submit" value="Войти" id="enter_submit">
                </div>
            </form>
        </dialog>

        <button id="show_enter">Войти</button>


        <dialog id="register">
            <h2 style="display:inline-block; float: left;">Регистрация</h2>
            <button style="display:inline-block;" id="exit_register">&#10005;</button>
            <form action="/index?action=register" method="post" id="formForReg" onsubmit="return false;">
                <table class="additional">
                    <tr>
                        <td>Фамилия: <span class="star">*</span></td>
                        <td><input type="text" name="lastName" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                                   onkeyup="checkInput(this)" onchange="checkInput(this)">
                            <span class="symbol">&#10007;</span>
                            <input type="hidden" value="${fieldsForCheckNull.put("lastName", false)}">
                        </td>
                    </tr>
                    <tr>
                        <td>Имя: <span class="star">*</span></td>
                        <td><input type="text" name="firstName" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                                   onchange="checkInput(this)" onkeyup="checkInput(this)">
                            <span class="symbol">&#10007;</span>
                            <input type="hidden" value="${fieldsForCheckNull.put("firstName", false)}">
                        </td>
                    </tr>
                    <tr>
                        <td>Отчество:</td>
                        <td><input type="text" name="patronymic"></td>
                    </tr>
                    <tr>
                        <td>Логин: <span class="star">*</span></td>
                        <td><input type="text" name="login" onkeypress="checkLoginByRegistration(this)" onfocus="checkLoginByRegistration(this)"
                                   onchange="checkLoginByRegistration(this)" onkeyup="checkLoginByRegistration(this)">
                            <span class="symbol">&#10007;</span>
                            <input type="hidden" value="${fieldsForCheckNull.put("login", false)}">
                        </td>
                    </tr>
                    <tr>
                        <td>Пароль: <span class="star">*</span></td>
                        <td><input type="password" name="password" onkeypress="checkInput(this)"
                                   onfocus="checkInput(this)"
                                   onchange="checkInput(this)" onkeyup="checkInput(this)">
                            <span class="symbol">&#10007;</span>
                            <input type="hidden" value="${fieldsForCheckNull.put("password", false)}">
                        </td>
                    </tr>
                    <tr>
                        <td>Повторите пароль: <span class="star">*</span></td>
                        <td><input type="password" name="confirmPassword" onkeypress="checkPasswords(this)"
                                   onfocus="checkPasswords(this)"
                                   onchange="checkPasswords(this)" onkeyup="checkPasswords(this)">
                            <span class="symbol">&#10007;</span>
                            <input type="hidden" value="${fieldsForCheckNull.put("confirmPassword", false)}">
                        </td>
                    </tr>
                    <tr>
                        <td>Кто вы? <span class="star">*</span></td>
                        <td>
                            <select name="access" class="select" onkeypress="checkInput(this)"
                                    onfocus="checkInput(this)"
                                    onchange="checkInput(this)" onkeyup="checkInput(this)">
                                <jsp:useBean id="users" class="controller.UserTypeValues"/>
                                <option value="">Не выбрано</option>
                                <c:forEach items="${users.values}" var="item">
                                    <option value="${item}">${item.value}</option>
                                </c:forEach>
                            </select><span class="symbol">&#10007;</span>
                            <input type="hidden" value="${fieldsForCheckNull.put("access", false)}">
                        </td>
                    </tr>
                </table>
                <input type="hidden" id="teacher-hidden" name="teacher-hidden">
                <input type="hidden" class="school-hidden" name="school-hidden">
                <input type="hidden" class="child-hidden" name="child-hidden">
                <span class="star">Поля, помеченные *, являются обязательными для заполнения</span><br><br>
                <div align="left">
                    <input type="submit" value="Зарегистрировать" id="register_submit">
                </div>
            </form>

        </dialog>
        <button id="show_register">Регистрация</button>
        <br>
        ${result}
    </div>



    <script type="text/javascript" src="js/index/dialogs.js"></script>
    <jsp:include page="includes/index/userListForRegistration.jsp"/>
    <jsp:include page="includes/index/checkRegister.jsp"/>
    <jsp:include page="includes/index/checkEnter.jsp"/>
    <script type="text/javascript" src="js/index/dataLists.js"></script>
    <script type="text/javascript" src="js/index/checkPasswords.js"></script>
    <script type="text/javascript" src="js/index/checkLoginByRegistration.js"></script>

    <script type="text/javascript" src="js/index/checkInput.js"></script>
    <h1 align="center">Учебное пособие по основам алгоритмизации и программирования<br>для средней школы</h1>
</header>

<br><br><br>


<table width="100%">
    <tr>
        <td id="nav" valign="top">
            <nav>
            </nav>
        </td>
        <td valign="top" align="center">
            <c:if test="${content == 'themes'}">
                <jsp:include page="includes/user/themes.jsp"/>
            </c:if>

            <c:if test="${content == 'theory'}">
                ${theory.content}
            </c:if>
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
    var width = $('.menu_item').css('width');
    $('#nav').attr('width', width);
</script>




</body>
</html>