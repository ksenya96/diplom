/**
 * Created by acer on 01.02.2017.
 */
function checkLoginByRegistration(login) {
    var value = login.value.trim();
    var selector = $($(login).parent('td').children('span'));
    selector.empty();
    var reForLogin = /^[a-zA-Z][.\-_a-zA-Z\d]{4,253}[\-_a-zA-Z\d]$/;
    if (reForLogin.test(value)) {
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