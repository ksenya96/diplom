/**
 * Created by acer on 01.02.2017.
 */
function checkPasswordByRegistration(password) {
    var value = password.value.trim();
    var selector = $($(password).parent('td').children('span'));
    selector.empty();
    var reForPassword = /.{4,255}/;
    if (reForPassword.test(value)) {
        selector.append('&#10003;');
        selector.css('color', 'green');
        return 1;
    }
    else {
        selector.append('&#10007;');
        selector.css('color', 'red');
        return 0;
    }
}