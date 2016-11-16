<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 05.11.2016
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function addTabs() {
        var div = $('.menu1');
        switch (${user.access}) {
            case "0":
                div.append('<div>Класс</div>');
                break;
            case "1":
                break;
            case "2":
                break;
        }
    }
</script>
