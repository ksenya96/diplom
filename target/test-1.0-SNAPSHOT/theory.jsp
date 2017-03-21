<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
                    <table width="100%" border="1px" bgcolor="#FEFEC6" style="border: solid #c16228">
                        <tr>
                            <td align="left">

                                <c:choose>
                                    <c:when test="${themes.get(0) == theory.theme}">
                                        <a>Предыдущая тема</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/theory?theme_id=${themes.get(themes.indexOf(theory.theme) - 1).id}">Предыдущая тема</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td align="center"><a href="/themes?action=class&class=${theory.theme.clazz}">Список тем</a></td>
                            <td align="right">
                                <c:choose>
                                    <c:when test="${themes.get(themes.size() - 1) == theory.theme}">
                                        <a>Следующая тема</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/theory?theme_id=${themes.get(themes.indexOf(theory.theme) + 1).id}">Следующая тема</a>
                                    </c:otherwise>
                                </c:choose>




                            </td>
                        </tr>
                    </table>

                        ${theory.content}
                </div>
                <div class="decoration">
                    <h2>Задания по теме "${theory.theme.title}"</h2>
                    <table>
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
                    <h2>Результаты выполнения заданий по теме "${theory.theme.title}"</h2>
                    <div>
                    <table>
                        <tr valign="top">
                            <td width="80%">
                                <div style="display: inline-block; width: 90%" class="decoration">
                                    <table id="results"  class="tablesorter" border="1px black solid">
                                        <thead>
                                        <tr>
                                            <th>№&emsp;</th>
                                            <th>ФИО&emsp;</th>
                                            <th>Класс&emsp;</th>
                                            <th>Школа&emsp;</th>
                                            <c:forEach var="i" begin="1" end="${tasks.size()}">
                                                <th>${i}&emsp;</th>
                                            </c:forEach>
                                            <th>Кол-во решенных&emsp;<br>заданий</th>
                                            <th>Отметка&emsp;</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </td>
                            <td width="20%" valign="top">
                                <div style="display: inline-block; width: 10%" class="decoration">

                                    <table>
                                        <tr>
                                            <td colspan="2">Результаты по:</td>
                                        </tr>
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
                                                <input name="school" onclick="this.select();" list="school_list"
                                                       placeholder="Начните вводить название школы">
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
                            </td>
                        </tr>
                    </table>
                        </div>
                </div>
            </div>

        </c:when>
        <c:otherwise>
            <table width="100%" border="1px" bgcolor="#FEFEC6" style="border: solid #c16228">
                <tr>
                    <td align="left">
                        <c:choose>
                            <c:when test="${themes.get(0) == theory.theme}">
                                <a>Предыдущая тема</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/theory?theme_id=${themes.get(themes.indexOf(theory.theme) - 1).id}">Предыдущая тема</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td align="center"><a href="/themes?action=class&class=${theory.theme.clazz}">Список тем</a></td>
                    <td align="right">
                        <c:choose>
                            <c:when test="${themes.get(themes.size() - 1) == theory.theme}">
                                <a>Следующая тема</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/theory?theme_id=${themes.get(themes.indexOf(theory.theme) + 1).id}">Следующая тема</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
            ${theory.content}
            <a href="/theory?action=subscribe">Подписаться на задания</a>
        </c:otherwise>
    </c:choose>
</c:if>

<script>
    function insertIntoTable() {
        var trWithData = '<tbody>';
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
                '<td align="center"><fmt:formatNumber value="${done * 10 / tasks.size()}" maxFractionDigits="2"/></td>' +
                '</tr>';
        <c:set var="n" value="${n+1}"/>
        </c:forEach>
        trWithData += '</tbody>';

        $('#results').append(trWithData);
    }

    function markOwnPupils() {
        var hidden = document.getElementsByClassName('contains');
        for (var i = 0; i < hidden.length; i++) {
            if (hidden[i].value == 'true') {
                var tds = hidden[i].parentNode.childNodes;
                for (var j = 0; j < tds.length; j++)
                    tds[j].style.backgroundColor = '#d8a92a';
            }
        }
    }

    insertIntoTable();
    markOwnPupils();
    $("#results").tablesorter();
</script>

<script>
    function removeRowsInResult() {
        var tbody = document.getElementsByClassName('row_in_result')[0];
        if (tbody != null)
            tbody.parentNode.parentNode.removeChild(tbody.parentNode);
    }

    $('.clazz').change(function () {
        var val = $('.clazz option:selected').attr('value').trim();
        removeRowsInResult();
        insertIntoTable();
        markOwnPupils();
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
        $('#results').trigger('update');


    });

    $('input').on('input keyup', function () {
        var val = this.value;
        removeRowsInResult();
        insertIntoTable();
        markOwnPupils();
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

        $('#results').trigger('update');

    });

    function chooseOwnPupils() {
        var hidden = document.getElementsByClassName('contains');
        for (var i = 0; i < hidden.length; ) {
            if (hidden[i].value == 'false')
                hidden[i].parentNode.parentNode.removeChild(hidden[i].parentNode);
            else
                i++;
        }
        $('#results').trigger('update');
    }


    $('input:checkbox').on('click', function () {
        if ($(this).is(':checked')) {
            chooseOwnPupils();
        }
        else {
            removeRowsInResult();
            insertIntoTable();
            markOwnPupils();
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
        $('#results').trigger('update');
    });

    /*$(document).ready(function(){
        $("#results").tablesorter();
    });*/
</script>


