<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 20.10.2016
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $('.select').change(function () {
        var val = $('.select option:selected').attr('value').trim();
        $('#class').remove();
        $('#school').remove();
        $('#teacher').remove();
        $('#child').remove();
        var selector = $($('.select').parent('td').children('span'));
        selector.empty();
        if (val === '') {
            selector.append('&#10007;');
            selector.css('color', 'red');
        }
        else {
            selector.append('&#10003;');
            selector.css('color', 'green');
        }


        switch (val) {
            case 'PUPIL':
                var clazzes = '';
                for (var i = 1; i < 12; i++)
                    clazzes += '<option value="' + i + '">' + i + '</option>';

                var dom = $('<tr id = "class">' +
                        '<td>Класс: <span class="star">*</span></td>' +
                        '<td>' +
                        '<select name="clazz">' +
                        clazzes +
                        '</select>' +
                        '</td>' +
                        '</tr>');
                $('.additional').append(dom);

                var dom = $('<tr id="school">' +
                        '<td>Школа: <span class="star">*</span></td>' +
                        '<td>' +
                        '<input name = "school" onclick="this.select();" list = "school_list" placeholder = "Начните вводить название школы">' +
                        '<datalist id="school_list">' +
                        '<c:forEach items="${schools}" var="item">' +
                        '<option id="${item.id}">${item.name}</option>' +
                        '</c:forEach>' +
                        '</datalist>' +
                        '<span class="symbol">&#10007;</span>' +
                        '</td>' +
                        '</tr>');
                $('.additional').append(dom);

                var dom = $('<tr id = "teacher">' +
                        '<td>Учитель: <span class="star">*</span></td>' +
                        '<td>' +
                        '<input name = "teacher" onclick="this.select();" list="teacher_list" placeholder = "Начните вводить фамилию">' +
                        '<datalist id = "teacher_list">' +
                        '<c:forEach items="${teachers}" var="item">' +
                        '<option id = "${item.id }">${item.lastName} ' + '${item.firstName} ' + '${item.patronymic}' + '</option>' +
                        '</c:forEach>' +
                        '</datalist>' +
                        '<span class="symbol">&#10007;</span>' +
                        '</td>' +
                        '</tr>');
                $('.additional').append(dom);
                break;
            case 'TEACHER':
                break;
            case 'PARENT':
                var dom = $('<tr id = "child">' +
                        '<td>Ребенок: <span class="star">*</span></td>' +
                        '<td>' +
                        '<input name="child" onclick="this.select();" list="child_list" placeholder = "Начните вводить фамилию">' +
                        '<datalist id="child_list">' +
                        '<c:forEach items="${pupils}" var="item">' +
                        '<option id="${item.id}">${item.lastName} ' + '${item.firstName} ' + '</option>' +
                        '</c:forEach>' +
                        '</datalist>' +
                        '<span class="symbol">&#10007;</span>' +
                        '</td>' +
                        '</tr>');
                $('.additional').append(dom);
                break;
        }
        
    });

</script>