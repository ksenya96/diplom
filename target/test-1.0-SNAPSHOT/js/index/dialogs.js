/**
 * Created by acer on 20.10.2016.
 */
(function () {
    var enter = document.getElementById('enter');
    var register = document.getElementById('register');
    document.getElementById('show_enter').onclick = function () {
        enter.style.display = "block";
        if (navigator.userAgent.search(/Opera/) > -1 || navigator.userAgent.search(/Chrome/) > -1)
            enter.showModal();
        else {
            $(enter).addClass('dialog');
            dialogPolyfill.registerDialog($(enter));
            $(enter).showModal();
        }

    };
    document.getElementById('show_register').onclick = function () {
        register.style.display = "block";
        if (navigator.userAgent.search(/Opera/) > -1 || navigator.userAgent.search(/Chrome/) > -1)
            register.showModal();
        else {
            $(register).addClass('dialog');
            dialogPolyfill.registerDialog($(register));
            $(register).showModal();
        }
    };
    document.getElementById('exit_enter').onclick = function () {
        if (navigator.userAgent.search(/Opera/) > -1 || navigator.userAgent.search(/Chrome/) > -1)
            enter.close();
        enter.style.display = "none";
    };
    document.getElementById('exit_register').onclick = function () {
        if (navigator.userAgent.search(/Opera/) > -1 || navigator.userAgent.search(/Chrome/) > -1)
            register.close();
        register.style.display = "none";
    };
})();