/**
 * Created by acer on 14.01.2017.
 */
function GoUp(commandEnum) {
    this.commandEnum = commandEnum;

    this.getTextForProgramField = function () {
        return this.commandEnum + ';';
    };

    this.drawWay = function (field) {
        var robotWay = field.getRobotWay();
        field.getRobotWay().push(new Point(robotWay[robotWay.length - 1].x, robotWay[robotWay.length - 1].y - 5));
    };

    this.move = function (point) {
        point.y -= 5;
        return point;
    };

    this.drawBlock = function (ctxt, point) {
        var x = point.x;
        var y = point.y;


        ctxt.strokeRect(x, y, RECTANGLE_WIDTH, FIGURE_HEIGHT);
        ctxt.fillText(this.commandEnum, x + RECTANGLE_WIDTH / 2 - FONT_SIZE * commandEnum.length / 2.5,
            y + FIGURE_HEIGHT / 2 + FONT_SIZE / 3);

    };
}