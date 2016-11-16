<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 31.10.2016
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $('#enter_submit').on('click', function () {
        //проверка на пустые значения полей формы входа
        var login = $(document.getElementsByName('login')[0]).val().trim();
        var password = $(document.getElementsByName('password')[0]).val().trim();

        //если поля не пустые, разрешить отправку формы в сервлет
        if (login === null || login === '' || password === null || password === '') {
            document.getElementById('formForEnter').setAttribute('onsubmit', 'return false;');
        }
        else {
            document.getElementById('formForEnter').setAttribute('onsubmit', 'return true;');
        }
    });
</script>
