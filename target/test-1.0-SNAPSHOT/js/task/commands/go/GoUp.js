/**
 * Created by acer on 14.01.2017.
 */
function GoUp(commandEnum) {
    Go.apply(this, arguments);

    this.drawWay = function (field) {
        var robotWay = field.getRobotWay();
        field.getRobotWay().push(new Point(robotWay[robotWay.length - 1].x, robotWay[robotWay.length - 1].y - 5));
    };

    this.move = function (point) {
        point.y -= 5;
        return point;
    };

}