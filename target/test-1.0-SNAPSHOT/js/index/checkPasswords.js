/**
 * Created by acer on 26.10.2016.
 */
function checkPasswords(confirmPassword) {
    var password = document.getElementsByName('password')[1].value;
    var selector = $($(confirmPassword).parent('td').children('span'));
    selector.empty();
    if (password != null && password !== '' && password === confirmPassword.value.trim()) {
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