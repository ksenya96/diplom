<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 10.11.2016
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<script>
    $('.edit_submit').on('click', function () {
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

        //если все нужные поля запонены, разрешить отправку формы в сервлет
        var submits = document.getElementsByClassName('formForEdit');
        for (var i = 0; i < submits.length; i++)
            if (allFieldsAreFilledIn === 0)
                submits[i].setAttribute('onsubmit', 'return false;');
            else
                submits[i].setAttribute('onsubmit', 'return true;');

    });
</script>
