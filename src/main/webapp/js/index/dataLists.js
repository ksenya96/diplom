/**
 * Created by acer on 20.10.2016.
 */

$(document).on('change', 'input', function () {
    var val = $(this).val();
    var list = $(this).attr('list');
    var options = document.getElementById(list).children;
    var selector = $($(this).parent('td').children('span'));
    selector.empty();
    for (var i = 0; i < options.length; i++) {
        if(options[i].text === val) {
            var id = options[i].getAttribute('id');
            selector.append('&#10003;');
            selector.css('color', 'green');
            switch (list) {
                case 'school_list': {
                    var schools = document.getElementsByClassName('school-hidden');
                    for (var j = 0; j < schools.length; j++)
                        schools.item(j).setAttribute('value', id);
                    break;
                }
                case 'teacher_list': {
                    document.getElementById('teacher-hidden').setAttribute('value', id);
                    break;
                }
                case 'child_list': {
                    var children = document.getElementsByClassName('child-hidden');
                    for (var j = 0; j < children.length; j++)
                        children.item(j).setAttribute('value', id);
                    break;
                }
            }
            break;
        }
        else {
            selector.empty();
            selector.append('&#10007;');
            selector.css('color', 'red');
            switch (list) {
                case 'school_list': {
                    var schools = document.getElementsByClassName('school-hidden');
                    for (var j = 0; j < schools.length; j++)
                        schools.item(j).setAttribute('value', '');
                    break;
                }
                case 'teacher_list': {
                    document.getElementById('teacher-hidden').setAttribute('value', '');
                    break;
                }
                case 'child_list': {
                    var children = document.getElementsByClassName('child-hidden');
                    for (var j = 0; j < children.length; j++)
                        children.item(j).setAttribute('value', '');
                    break;
                }
            }
        }
    }

});




