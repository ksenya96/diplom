<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 13.08.2016
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/index/star.css">
    <meta charset="utf-8">
    <title>Учимся играя</title>
</head>
<body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<h1 align="center">Учебное пособие</h1>
<h1 align="center">по основам алгоритмизации и программирования</h1>
<h1 align="center">для средней школы</h1>

<br><br><br>

<c:forEach items="${themes}" var="item">
    <div align="center">
        <a href="/servlet?theme=${item.id}">${item.title}</a><br>
    </div>
</c:forEach>

<dialog id="enter">
    <h2>Вход в систему</h2>
    <form action="/servlet" method="post" id="formForEnter" onsubmit="return false;">
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
                    <input type="password" name="password" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                           onkeyup="checkInput(this)" onchange="checkInput(this)">
                    <span class="symbol">&#10007;</span>
                </td>
            </tr>
        </table>
        <input type="hidden" name="type" value="login">
        <input type="submit" value="Войти" onclick="checkEnter()">
    </form>
    <button id="exit_enter">Exit</button>
</dialog>
<button id="show_enter">Войти</button>


<jsp:useBean id="fieldsForCheckNull" class="java.util.ArrayList" scope="session"/>
<dialog id="register">
    <form action="/servlet" method="post" id="formForReg" onsubmit="return false;">
        <table class="additional">
            <tr>
                <td>Фамилия: <span class="star">*</span></td>
                <td><input type="text" name="lastName" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                           onkeyup="checkInput(this)" onchange="checkInput(this)">
                    <span class="symbol">&#10007;</span>
                    <input type="hidden" value="${fieldsForCheckNull.add("lastName")}">
                </td>
            </tr>
            <tr>
                <td>Имя: <span class="star">*</span></td>
                <td><input type="text" name="firstName" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                           onchange="checkInput(this)" onkeyup="checkInput(this)">
                    <span class="symbol">&#10007;</span>
                    <input type="hidden" value="${fieldsForCheckNull.add("firstName")}">
                </td>
            </tr>
            <tr>
                <td>Отчество:</td>
                <td><input type="text" name="patronymic"></td>
            </tr>
            <tr>
                <td>Логин: <span class="star">*</span></td>
                <td><input type="text" name="login" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                           onchange="checkInput(this)" onkeyup="checkInput(this)">
                    <span class="symbol">&#10007;</span>
                    <input type="hidden" value="${fieldsForCheckNull.add("login")}">
                </td>
            </tr>
            <tr>
                <td>Пароль: <span class="star">*</span></td>
                <td><input type="password" name="password" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                           onchange="checkInput(this)" onkeyup="checkInput(this)">
                    <span class="symbol">&#10007;</span>
                    <input type="hidden" value="${fieldsForCheckNull.add("password")}">
                </td>
            </tr>
            <tr>
                <td>Повторите пароль: <span class="star">*</span></td>
                <td><input type="password" name="confirmPassword" onkeypress="checkPasswords(this)" onfocus="checkPasswords(this)"
                           onchange="checkPasswords(this)" onkeyup="checkPasswords(this)">
                    <span class="symbol">&#10007;</span>
                    <input type="hidden" value="${fieldsForCheckNull.add("confirmPassword")}">
                </td>
            </tr>
            <tr>
                <td>Кто вы? <span class="star">*</span></td>
                <td>
                    <select name="access" class="select" onkeypress="checkInput(this)" onfocus="checkInput(this)"
                            onchange="checkInput(this)" onkeyup="checkInput(this)">
                        <jsp:useBean id="users" class="controller.UserTypeValues"/>
                        <option value="">Не выбрано</option>
                        <c:forEach items="${users.values}" var="item">
                            <option value="${item}">${item.value}</option>
                        </c:forEach>
                    </select><span class="symbol">&#10007;</span>
                    <input type="hidden" value="${fieldsForCheckNull.add("access")}">
                </td>
            </tr>
        </table>
        <input type="hidden" id="hiddenField" name="type" value="register">
        <input type="hidden" id="teacher-hidden" name="teacher-hidden">
        <input type="hidden" id="school-hidden" name="school-hidden">
        <input type="hidden" id="child-hidden" name="child-hidden">
        <input type="submit" value="Зарегистрировать" onclick="checkFieldsForNull()">
    </form>
    <span class="star">Поля, помеченные *, являются обязательными для заполнения</span><br><br>
    <button id="exit_register">Exit</button>
</dialog>
<button id="show_register">Регистрация</button>


<script type="text/javascript" src="js/index/dialogs.js"></script>
<jsp:include page="includes/index/userListForRegistration.jsp"/>
<script>
    function checkFieldsForNull() {
        var allFieldsAreFilledIn = 1;
        <c:forEach items="${fieldsForCheckNull}" var="item">
            var arr = document.getElementsByName('${item}');
            var val = $(arr[arr.length - 1]).val();
            var str = val + ' ' + '${item}';
        alert(str);
            if (val === null || val === '') {
                allFieldsAreFilledIn = 0;
            }
        </c:forEach>

        var password = document.getElementsByName('password')[1].value.trim();
        var confirmPassword = document.getElementsByName('confirmPassword')[0].value.trim();
        if (password != null && password !== '' && password === confirmPassword)
            allFieldsAreFilledIn *= 1;
        else
            allFieldsAreFilledIn = 0;
        if (allFieldsAreFilledIn === 0) {
            document.getElementById('formForReg').setAttribute('onsubmit', 'return false;');
        }
        else {
            document.getElementById('formForReg').setAttribute('onsubmit', 'return true;');
        }
    }

    function checkEnter() {
        var login = $(document.getElementsByName('login')[0]).val().trim();
        var password = $(document.getElementsByName('password')[0]).val().trim();
        alert(login);
        alert(password);
        if (login === null || login === '' || password === null || password === '') {
            document.getElementById('formForEnter').setAttribute('onsubmit', 'return false;');
        }
        else {
            document.getElementById('formForEnter').setAttribute('onsubmit', 'return true;');
        }
    }
</script>
<script type="text/javascript" src="js/index/dataLists.js"></script>
<script type="text/javascript" src="js/index/checkPasswords.js"></script>
<script type="text/javascript" src="js/index/checkInput.js"></script>
${result}
</body>
</html>