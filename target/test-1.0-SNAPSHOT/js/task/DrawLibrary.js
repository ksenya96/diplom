/**
 * Created by acer on 16.01.2017.
 */
function drawLine(x0, y0, x1, y1, ctxt) {
    ctxt.beginPath();
    ctxt.moveTo(x0, y0);
    ctxt.lineTo(x1, y1);
    ctxt.stroke();
    ctxt.closePath();
}

function drawArcLeft(x, y, width, height, ctxt) {
    ctxt.beginPath();
    ctxt.moveTo(x, y + height/2);
    ctxt.bezierCurveTo(
        x - width/2, y + height/2, // C3
        x - width/2, y - height/2, // C4
        x, y - height/2);
    ctxt.stroke();
    ctxt.closePath();
}

function drawArcRight(x, y, width, height, ctxt) {
    ctxt.beginPath();
    ctxt.moveTo(x, y - height/2);
    ctxt.bezierCurveTo(
        x + width/2, y - height/2, // C1
        x + width/2, y + height/2, // C2
        x, y + height/2);
    ctxt.stroke();
    ctxt.closePath();
}