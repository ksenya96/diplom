<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 26.10.2016
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $('#register_submit').on ('click', function() {
        //проверка на пустые значения обязательных для запонения полей в форме регистрации
        var allFieldsAreFilledIn = 1;
        <c:forEach items="${fieldsForCheckNull}" var="item">
            var arr = document.getElementsByName('${item}');
            var val = $(arr[arr.length - 1]).val();
            //var str = val + ' ' + '${item}';
            if (val === null || val === '') {
                allFieldsAreFilledIn = 0;
            }
        </c:forEach>

        var password = document.getElementsByName('password')[1].value.trim();
        var confirmPassword = document.getElementsByName('confirmPassword')[0].value.trim();
        //проверка на совпадение паролей
        if (password != null && password !== '' && password === confirmPassword)
            allFieldsAreFilledIn *= 1;
        else
            allFieldsAreFilledIn = 0;

        //если все нужные поля запонены, разрешить отправку формы в сервлет
        if (allFieldsAreFilledIn === 0) {
            document.getElementById('formForReg').setAttribute('onsubmit', 'return false;');
        }
        else {
            document.getElementById('formForReg').setAttribute('onsubmit', 'return true;');
        }
    });
</script>
