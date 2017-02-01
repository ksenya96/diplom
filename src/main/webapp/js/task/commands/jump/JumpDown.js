/**
 * Created by acer on 16.01.2017.
 */
function JumpDown(commandEnum) {
    Jump.apply(this, arguments);

    this.drawWay = function (field) {
        var robotWay = field.getRobotWay();
        field.getRobotWay().push(new Point(robotWay[robotWay.length - 1].x, robotWay[robotWay.length - 1].y + SQUARE_SIZE * 2));
    };

    this.move = function (point) {
        point.y += SQUARE_SIZE * 2;
        return point;
    };

}