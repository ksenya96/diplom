<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 13.08.2016
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <form action="/servlet" method="post">
        <table>
            <tr>
                <td>Логин:</td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <td><input type="password" name="password"></td>
            </tr>
        </table>
        <input type="hidden" name="type" value="login">
        <input type="submit" value="Войти">
    </form>
    <button id="exit_enter">Exit</button>
</dialog>
<button id="show_enter">Войти</button>

<dialog id="register">
    <form action="/servlet" method="post" id="reg">
        <table class="additional">
            <tr>
                <td>Фамилия:</td>
                <td><input type="text" name="lastName"></td>
            </tr>
            <tr>
                <td>Имя:</td>
                <td><input type="text" name="firstName"></td>
            </tr>
            <tr>
                <td>Отчество:</td>
                <td><input type="text" name="patronymic"></td>
            </tr>
            <tr>
                <td>Логин:</td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Повторите пароль:</td>
                <td><input type="password" name="confirmPassword"></td>
            </tr>
            <tr>
                <td>Кто вы?</td>
                <td>
                    <select name="access" class="select">
                        <jsp:useBean id="users" class="controller.UserTypeValues"/>
                        <option value="null">Не выбрано</option>
                        <c:forEach items="${users.values}" var="item">
                            <option value="${item}">${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input type="hidden" name="type" value="register" id="hiddenField">
        <input type="submit" value="Зарегистрировать">
    </form>
    <button id="exit_register">Exit</button>
</dialog>
<button id="show_register">Регистрация</button>


<script type="text/javascript">
    (function () {
        var enter = document.getElementById('enter');
        var register = document.getElementById('register');
        document.getElementById('show_enter').onclick = function () {
            enter.show();
        };
        document.getElementById('show_register').onclick = function () {
            register.show();
        };
        document.getElementById('exit_enter').onclick = function () {
            enter.close();
        };
        document.getElementById('exit_register').onclick = function () {
            register.close();
        };
    })();

    $('.select').change(function () {
        var val = $('.select option:selected').attr('value');
        switch (val) {
            case 'PUPIL':
                var clazzes = '';
                for (var i = 1; i < 12; i++)
                    clazzes += '<option value="' + i + '">' + i + '</option>';

                var dom = $('<tr>' +
                              '<td>Класс: </td>' +
                              '<td>' +
                                 '<select name="clazz">' +
                                      clazzes +
                                 '</select>' +
                              '</td>' +
                            '</tr>');
                $('.additional').append(dom);

                var dom = $('<tr>' +
                        '<td>Школа: </td>' +
                        '<td>' +
                        '<select name="school">' +
                            '<option value="0">Не выбрано</option>'+
                            '<c:forEach items="${schools}" var="item">' +
                                 '<option value="${item.id}">${item.name}</option>' +
                            '</c:forEach>' +
                        '</select>' +
                        '</td>' +
                        '</tr>');
                $('.additional').append(dom);

                var dom = $('<tr>' +
                        '<td>Учитель: </td>' +
                        '<td>' +
                        '<select name="teacher">' +
                            '<option value="0">Не выбрано</option>'+
                            '<c:forEach items="${teachers}" var="item">' +
                                '<option value="${item.id}">${item.user.lastName} ' + '${item.user.firstName} ' + '${item.user.patronymic} ' + '</option>' +
                            '</c:forEach>' +
                        '</select>' +
                        '</td>' +
                        '</tr>');
                $('.additional').append(dom);
                break;
            case 2:
                break;
            case 3:
                break;
        }
    });
</script>


${result}
</body>
</html>