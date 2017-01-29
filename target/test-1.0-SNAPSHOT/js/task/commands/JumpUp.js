/**
 * Created by acer on 16.01.2017.
 */
function JumpUp(commandEnum) {
    this.commandEnum = commandEnum;

    this.getTextForProgramField = function () {
        return '  ' + commandEnum + ';';
    };

    this.drawWay = function (field) {
        var robotWay = field.getRobotWay();
        field.getRobotWay().push(new Point(robotWay[robotWay.length - 1].x, robotWay[robotWay.length - 1].y - SQUARE_SIZE * 2));
    };

    this.move = function (point) {
        point.y -= SQUARE_SIZE * 2;
        return point;
    };

    this.drawBlock = function (ctxt, point) {
        var x = point.x;
        var y = point.y;

        ctxt.strokeRect(x - 20 - RECTANGLE_WIDTH / 3, y, RECTANGLE_WIDTH / 1.5, FIGURE_HEIGHT);
        ctxt.fillText(this.commandEnum, x - 20 - RECTANGLE_WIDTH / 3 + RECTANGLE_WIDTH / 3 - FONT_SIZE * this.commandEnum.length / 2.5,
        y + FIGURE_HEIGHT / 2 + FONT_SIZE / 3);
        drawLine(x - 20, y + FIGURE_HEIGHT, x - 20, y + FIGURE_HEIGHT + 5, ctxt);
        drawLine(x - 20, y + FIGURE_HEIGHT + 5, x + RECTANGLE_WIDTH + 20, y + FIGURE_HEIGHT + 5, ctxt);
        drawLine(x + RECTANGLE_WIDTH + 20, y + FIGURE_HEIGHT + 5, x + RECTANGLE_WIDTH + 20, y - 10, ctxt);
    }
}