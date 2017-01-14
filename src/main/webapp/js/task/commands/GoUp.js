/**
 * Created by acer on 14.01.2017.
 */
function GoDown(commandEnum) {
    this.commandEnum = commandEnum;

    this.getTextForProgramField = function () {
        return this.commandEnum + ';';
    };

    this.drawWay = function (field) {
        var robotWay = field.getRobotWay();
        field.getRobotWay().push(new Point(robotWay[robotWay.length - 1].x, robotWay[robotWay.length - 1].y + 5));
    };

    this.move = function (point) {
        point.y += 5;
        return point;
    };

    this.drawBlock = function (ctxt, point, rectangleWidth) {
        var x = point.x;
        var y = point.y;

    };
}