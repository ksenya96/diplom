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


                <div style="padding: 5px">
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
                                        <span style="color: green">&#10003;</span>
                                    </c:if>
                                </td>
                            </tr>
                            <c:set var="n" value="${n+1}"/>
                        </c:forEach>
                    </table>

                </div>

                <div>
                    <div style="display: inline-block">
                        <table border="1px" id="results">
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


                        </table>
                    </div>
                    <div style="display: inline-block; vertical-align: top">
                        Результаты по:<br>
                        <table>
                            <tr>
                                <td>классу</td>
                                <td>
                                    <select class="clazz">
                                        <option value="">Не выбрано</option>
                                        <c:forEach var="clazz" begin="1" end="11">
                                            <option value="${clazz}">${clazz}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>школе</td>
                                <td>
                                    <input name = "school" onclick="this.select();" list = "school_list" placeholder = "Начните вводить название школы">
                                    <datalist id="school_list">
                                        <c:forEach items="${schools}" var="item">
                                            <option id="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                            </tr>
                            <c:if test="${user.access == 'TEACHER'}">
                                <tr>
                                    <td>Показать только<br>своих учеников</td>
                                    <td><input type="checkbox"></td>
                                </tr>
                            </c:if>
                            <c:if test="${user.access == 'PARENT'}">
                                <tr>
                                    <td>Показать только<br>своих детей</td>
                                    <td><input type="checkbox"></td>
                                </tr>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>

        </c:when>
        <c:otherwise>
            ${theory.content}
            <a href="/theory?action=subscribe">Подписаться на задания</a>
        </c:otherwise>
    </c:choose>
</c:if>

<script>
    function insertIntoTable() {
        var trWithData = '';
        <c:set var="n" value="1"/>
        <c:forEach var="item" items="${theory.theme.pupils}">

        trWithData += '<tr class="row_in_result">';
        <c:choose>
        <c:when test="${user.access != 'PUPIL' && user.pupils.contains(item)}">
        trWithData += '<input type="hidden" class="contains" value="true">';
        </c:when>
        <c:otherwise>
        trWithData += '<input type="hidden" class="contains" value="false">';
        </c:otherwise>
        </c:choose>
        trWithData += '<td align="center">${n}</td>' +
                '<td>${item.lastName} ${item.firstName} ${item.patronymic}</td>' +
                '<td align="center" class="current_clazz">${item.clazz}</td>' +
                '<td class="current_school">${item.school.name}</td>';
        <c:set var="done" value="0"/>
        <c:forEach var="t" items="${tasks}">
        <c:choose>
        <c:when test="${item.doneTasks.contains(t)}">
        trWithData += '<td align="center"><span style="color: green">&#10003;</span></td>';
        <c:set var="done" value="${done + 1}"/>
        </c:when>
        <c:otherwise>
        trWithData += '<td><span> </span></td>';
        </c:otherwise>
        </c:choose>
        </c:forEach>
        trWithData += '<td align="center">${done}</td>' +
                '<td align="center">${done * 10 / tasks.size()}</td>' +
                '</tr>';
        <c:set var="n" value="${n+1}"/>
        </c:forEach>

        $('#results').append(trWithData);
    }

    insertIntoTable();
</script>

<script>
    function removeRowsInResult() {
        var rowsInResult = document.getElementsByClassName('row_in_result');
        for (var i = 0; i < rowsInResult.length;) {
            rowsInResult[i].remove();
        }
    }

    $('.clazz').change(function () {
        var val = $('.clazz option:selected').attr('value').trim();
        removeRowsInResult();
        insertIntoTable();
        if ($('input:checkbox').is(':checked'))
            chooseOwnPupils();

        var school = $('input:text').val().trim();

        var tdWithClazzes = document.getElementsByClassName('current_clazz');
        var tdWithSchools = document.getElementsByClassName('current_school');

        for (var i = 0; i < tdWithClazzes.length;) {
            if (val !== '' && tdWithClazzes[i].innerHTML.trim() !== val || school !== '' && tdWithSchools[i].innerHTML.trim() !== school)
                tdWithClazzes[i].parentNode.parentNode.removeChild(tdWithClazzes[i].parentNode);
            else
                i++;
        }


    });

    $('input').on('input keyup', function () {
        var val = this.value;
        removeRowsInResult();
        insertIntoTable();
        if ($('input:checkbox').is(':checked'))
            chooseOwnPupils();

        var clazz = $('.clazz option:selected').attr('value').trim();

        var tdWithSchools = document.getElementsByClassName('current_school');
        var tdWithClazzes = document.getElementsByClassName('current_clazz');

        for (var i = 0; i < tdWithSchools.length;) {
            if (val !== '' && tdWithSchools[i].innerHTML.trim() !== val || clazz !== '' && tdWithClazzes[i].innerHTML.trim() !== clazz)
                tdWithSchools[i].parentNode.parentNode.removeChild(tdWithSchools[i].parentNode);
            else
                i++;
        }

    });

    function chooseOwnPupils() {
        var hidden = document.getElementsByClassName('contains');
        for (var i = 0; i < hidden.length; ) {
            if (hidden[i].value == 'false')
                hidden[i].parentNode.parentNode.removeChild(hidden[i].parentNode);
            else
                i++;
        }
    }

    $('input:checkbox').on('click', function () {
        if ($(this).is(':checked')) {
            chooseOwnPupils();
        }
        else {
            removeRowsInResult();
            insertIntoTable();
            var clazz = $('.clazz option:selected').attr('value').trim();
            var school = $('input:text').val().trim();
            var tdWithSchools = document.getElementsByClassName('current_school');
            var tdWithClazzes = document.getElementsByClassName('current_clazz');

            for (var i = 0; i < tdWithSchools.length;) {
                if (school !== '' && tdWithSchools[i].innerHTML.trim() !== school || clazz !== '' && tdWithClazzes[i].innerHTML.trim() !== clazz)
                    tdWithSchools[i].parentNode.parentNode.removeChild(tdWithSchools[i].parentNode);
                else
                    i++;
            }
        }
    });
</script>


