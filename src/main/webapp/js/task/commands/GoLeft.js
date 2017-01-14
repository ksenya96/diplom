/**
 * Created by acer on 14.01.2017.
 */
function GoRight(commandEnum) {
    this.commandEnum = commandEnum;

    this.getTextForProgramField = function () {
        return this.commandEnum + ';';
    };

    this.drawWay = function (field) {
        var robotWay = field.getRobotWay();
        field.getRobotWay().push(new Point(robotWay[robotWay.length - 1].x + 5, robotWay[robotWay.length - 1].y));
    };

    this.move = function (point) {
        point.x += 5;
        return point;
    };

    this.drawBlock = function (ctxt, point, rectangleWidth) {
        var x = point.x;
        var y = point.y;

    };
}