/**
 * Created by acer on 16.01.2017.
 */
function JumpRight(commandEnum) {
    Jump.apply(this, arguments);


    this.drawWay = function (field) {
        var robotWay = field.getRobotWay();
        field.getRobotWay().push(new Point(robotWay[robotWay.length - 1].x + SQUARE_SIZE * 2, robotWay[robotWay.length - 1].y));
    };

    this.move = function (point) {
        point.x += SQUARE_SIZE * 2;
        return point;
    };

}