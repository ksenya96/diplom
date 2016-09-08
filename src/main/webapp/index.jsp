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
   <h1 align="center">Учебное пособие</h1>
   <h1 align="center">по основам алгоритмизации и программирования</h1>
   <h1 align="center">для средней школы</h1>

   <br><br><br>


   <c:forEach items = "${themes}" var="item">
      <div align="center">
         <a href="/servlet?theme=${item.id}">${item.title}</a><br>
      </div>
   </c:forEach>
</body>
</html>