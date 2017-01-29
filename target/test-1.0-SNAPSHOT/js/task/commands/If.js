/**
 * Created by acer on 16.01.2017.
 */
function If(commandEnum) {
    this.commandEnum = commandEnum;

    this.getTextForProgramField = function () {
        return 'if is_wall then';
    };

    this.move = function () {

    };

    this.drawBlock = function (ctxt, point) {
        var x = point.x;
        var y = point.y;

        drawLine(x, y + FIGURE_HEIGHT / 2, x + RECTANGLE_WIDTH / 2, y, ctxt);
        drawLine(x + RECTANGLE_WIDTH / 2, y, x + RECTANGLE_WIDTH, y + FIGURE_HEIGHT / 2, ctxt);
        drawLine(x + RECTANGLE_WIDTH, y + FIGURE_HEIGHT / 2, x + RECTANGLE_WIDTH / 2, y + FIGURE_HEIGHT, ctxt);
        drawLine(x + RECTANGLE_WIDTH / 2, y + FIGURE_HEIGHT, x, y + FIGURE_HEIGHT / 2, ctxt);

        //стрелочка
        drawLine(x, y + FIGURE_HEIGHT / 2, x - 20, y + FIGURE_HEIGHT / 2, ctxt);
        drawLine(x - 20, y + FIGURE_HEIGHT / 2, x - 20, y + FIGURE_HEIGHT / 2 + 25, ctxt);
        drawLine(x - 20, y + FIGURE_HEIGHT / 2 + 25, x - 20 - SMALL_ARROW_LENGTH / Math.sin(ALPHA),
        y + FIGURE_HEIGHT / 2 + 25 - SMALL_ARROW_LENGTH / Math.cos(ALPHA), ctxt);
        drawLine(x - 20, y + FIGURE_HEIGHT / 2 + 25, x - 20 + SMALL_ARROW_LENGTH / Math.sin(ALPHA),
            y + FIGURE_HEIGHT / 2 + 25 - SMALL_ARROW_LENGTH / Math.cos(ALPHA), ctxt);
        drawLine(x + RECTANGLE_WIDTH, y + FIGURE_HEIGHT / 2, x + RECTANGLE_WIDTH + 20, y + FIGURE_HEIGHT / 2, ctxt);
        drawLine(x + RECTANGLE_WIDTH + 20, y + FIGURE_HEIGHT / 2, x + RECTANGLE_WIDTH + 20, y + FIGURE_HEIGHT / 2 + 25, ctxt);
        ctxt.fillText(this.commandEnum, x + RECTANGLE_WIDTH / 2 - FONT_SIZE * this.commandEnum.length / 2.5,
        y + FIGURE_HEIGHT / 2 + FONT_SIZE / 3);
    };
}