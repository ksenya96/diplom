/**
 * Created by acer on 20.10.2016.
 */

$(document).on('change', 'input', function () {
    var val = $(this).val();
    var list = $(this).attr('list');
    var options = document.getElementById(list).children;
    for (var i = 0; i < options.length; i++) {
        if(options[i].text === val) {
            var id = options[i].getAttribute('id');
            switch (list) {
                case 'school_list': {
                    document.getElementById('school-hidden').setAttribute('value', id);
                    break;
                }
                case 'teacher_list': {
                    document.getElementById('teacher-hidden').setAttribute('value', id);
                    break;
                }
                case 'child_list': {
                    document.getElementById('child-hidden').setAttribute('value', id);
                    break;
                }
            }
            break;
        }
    }

});




