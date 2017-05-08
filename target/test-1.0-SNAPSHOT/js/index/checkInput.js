/**
 * Created by acer on 26.10.2016.
 */
function checkInput(input) {
    var val = input.value.trim();
    var selector = $($(input).parent('td').children('span'));
    selector.empty();
    if (val === null || val === '') {
        selector.append('&#10007;');
        selector.css('color', 'red');
    }
    else {
        selector.append('&#10003;');
        selector.css('color', 'green');
    }
}