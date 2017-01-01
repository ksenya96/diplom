<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 07.12.2016
  Time: 16:13
  To change this template use File | Settings | File Templates.


  проверить дополнения
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $('.edit_additional').on('click', function () {
        <c:choose>
            <c:when test="${user.access.ordinal() == 0}">
                var arr = document.getElementsByName('teacher-hidden');

            </c:when>
            <c:otherwise>
                    <c:choose>
                        <c:when test="${user.access.ordinal() == 1}">
                            var arr = document.getElementsByName('child-hidden');
                        </c:when>
                        <c:otherwise>
                            var arr = document.getElementsByName('child-hidden');
                        </c:otherwise>
                    </c:choose>
            </c:otherwise>
        </c:choose>
        var val = $(arr[arr.length - 1]).val();
        var submits = document.getElementsByClassName('formForEdit');
        for (var i = 0; i < submits.length; i++)
            if (val === null || val === '')
                submits[i].setAttribute('onsubmit', 'return false;');
            else
                submits[i].setAttribute('onsubmit', 'return true;');
    });
</script>
