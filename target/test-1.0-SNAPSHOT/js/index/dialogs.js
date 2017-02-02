/**
 * Created by acer on 20.10.2016.
 */
(function () {
    var enter = document.getElementById('enter');
    var register = document.getElementById('register');
    document.getElementById('show_enter').onclick = function () {
        enter.showModal();
    };
    document.getElementById('show_register').onclick = function () {
        register.showModal();
    };
    document.getElementById('exit_enter').onclick = function () {
        enter.close();
    };
    document.getElementById('exit_register').onclick = function () {
        register.close();
    };
})();