/**
 * Created by acer on 16.01.2017.
 */
function For(commandEnum) {
    this.commandEnum = commandEnum;
    this.nFor = 0;

    this.getTextForProgramField = function () {
        return 'for i:=1 to ' + this.nFor + ' do';
    };

    this.setNFor = function (nFor) {
        this.nFor = nFor;
    };

    this.drawBlock = function (ctxt, point) {
        var x = point.x;
        var y = point.y;

        drawLine(x, y + FIGURE_HEIGHT / 2, x + RECTANGLE_WIDTH / 4, y, ctxt);
        drawLine(x + RECTANGLE_WIDTH / 4, y, RECTANGLE_WIDTH * 0.75, y, ctxt);
        drawLine(x + RECTANGLE_WIDTH * 0.75, y, x + RECTANGLE_WIDTH, y + FIGURE_HEIGHT / 2, ctxt);
        drawLine(x + RECTANGLE_WIDTH, y + FIGURE_HEIGHT / 2, x + RECTANGLE_WIDTH * 0.75, y + FIGURE_HEIGHT);
        drawLine(x + RECTANGLE_WIDTH * 0.75, y + FIGURE_HEIGHT, x + RECTANGLE_WIDTH / 4, y + FIGURE_HEIGHT);
        drawLine(x + RECTANGLE_WIDTH / 4, y + FIGURE_HEIGHT, x, y + FIGURE_HEIGHT / 2);
        ctxt.fillText(this.commandEnum + this.nFor + ',1', x + RECTANGLE_WIDTH / 2 - FONT_SIZE * (this.commandEnum + this.nFor + ',1').length,
        y + FIGURE_HEIGHT / 2 + FONT_SIZE / 3);
    }
}