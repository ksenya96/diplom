<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 31.12.2016
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${user != null}">
    <c:choose>
        <%--Показывать задания, если ученик подписан или пользователь не является учеником--%>
        <c:when test="${user.access == 'PUPIL' && user.themes.contains(theory.theme) || user.access != 'PUPIL'}">
            <div class="menu" align="center">
                <br id="tab2"/><br id="tab3"/>
                <a href="#tab1">Теория</a>
                <a href="#tab2">Задания</a>
                <a href="#tab3">Результаты</a>


                <div>
                        ${theory.content}
                </div>
                <div>
                    <table border="1px">
                        <tr>
                            <th>№</th>
                            <th>Задание</th>
                            <th>Отметка о<br>выполнении</th>
                        </tr>

                        <c:set var="n" value="1"/>
                        <c:forEach var="item" items="${tasks}">
                            <tr>
                                <td align="center">${n}</td>
                                <td>
                                    <a href="/task?task_id=${item.id}" id="task">${item.title}</a><br>
                                </td>
                                <td align="center">
                                    <c:if test="${user.doneTasks.contains(item)}">
                                        <script>
                                            //выделить сделанные задания зеленым цветом и поставить птичку
                                            $('#task').css('color', 'green');
                                        </script>
                                        <span style="color: green">&#10003;</span>
                                    </c:if>
                                </td>
                            </tr>
                            <c:set var="n" value="${n+1}"/>
                        </c:forEach>
                    </table>

                </div>

                <div>
                    <table border="1px">
                        <tr>
                            <th>№</th>
                            <th>ФИО</th>
                            <th>Класс</th>
                            <th>Школа</th>
                            <c:forEach var="i" begin="1" end="${tasks.size()}">
                                <th>${i}</th>
                            </c:forEach>
                            <th>Кол-во решенных<br>заданий</th>
                            <th>Отметка</th>
                        </tr>

                        <c:set var="n" value="1"/>
                        <c:forEach var="item" items="${theory.theme.pupils}">
                            <tr>
                                <td align="center">${n}</td>
                                <td>${item.lastName} ${item.firstName} ${item.patronymic}</td>
                                <td align="center">${item.clazz}</td>
                                <td>${item.school.name}</td>
                                <c:set var="done" value="0"/>
                                <c:forEach var="t" items="${tasks}">
                                    <c:choose>
                                        <c:when test="${item.doneTasks.contains(t)}">
                                            <td align="center"><span style="color: green">&#10003;</span></td>
                                            <c:set var="done" value="${done + 1}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span> </span></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <td align="center">${done}</td>
                                <td align="center">${done * 10 / tasks.size()}</td>
                            </tr>
                            <c:set var="n" value="${n+1}"/>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            ${theory.content}
            <a href="/theory?action=subscribe">Подписаться на задания</a>
        </c:otherwise>
    </c:choose>
</c:if>


