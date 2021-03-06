/**
 * Created by acer on 29.01.2017.
 */
function Jump(commandEnum) {
    this.commandEnum = commandEnum;

    this.getTextForProgramField = function () {
        return '  ' + commandEnum + ';';
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
    };

    this.setButtonParameters = function (task) {
        if (task.getNumberOfCommands() < task.getLinesLimit()) {
            task.getProgramField().addText(this.commandEnum);
            task.getBlock().addCommand(this.commandEnum);
            task.getCommands().push(this.commandEnum);
            var jumps = $('.buttons').children();
            for (var i = 0; i < jumps.length - 1; i++)
                jumps[i].setAttribute('disabled', '');
            task.setNumberOfCommands(task.getNumberOfCommands() + 1);
            task.draw();
        }
    };
}