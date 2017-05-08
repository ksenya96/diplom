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

    this.move = function () {

    };

    this.drawWay = function () {

    };

    this.drawBlock = function (ctxt, point) {
        var x = point.x;
        var y = point.y;

        drawLine(x, y + FIGURE_HEIGHT / 2, x + RECTANGLE_WIDTH / 4, y, ctxt);
        drawLine(x + RECTANGLE_WIDTH / 4, y, x + RECTANGLE_WIDTH * 0.75, y, ctxt);
        drawLine(x + RECTANGLE_WIDTH * 0.75, y, x + RECTANGLE_WIDTH, y + FIGURE_HEIGHT / 2, ctxt);
        drawLine(x + RECTANGLE_WIDTH, y + FIGURE_HEIGHT / 2, x + RECTANGLE_WIDTH * 0.75, y + FIGURE_HEIGHT, ctxt);
        drawLine(x + RECTANGLE_WIDTH * 0.75, y + FIGURE_HEIGHT, x + RECTANGLE_WIDTH / 4, y + FIGURE_HEIGHT, ctxt);
        drawLine(x + RECTANGLE_WIDTH / 4, y + FIGURE_HEIGHT, x, y + FIGURE_HEIGHT / 2, ctxt);
        ctxt.fillText(this.commandEnum + this.nFor + ',1', x + RECTANGLE_WIDTH / 2 - FONT_SIZE * (this.commandEnum + this.nFor + ',1').length / 2.5,
        y + FIGURE_HEIGHT / 2 + FONT_SIZE / 3);
    };

    this.setButtonParameters = function (task) {
        var val = $('.for option:selected').attr('value').trim();
        if (+val > 0 && task.getNumberOfCommands() < task.getLinesLimit()) {
            task.setNFor(+val);
            task.getBlock().setNFor(+val);
            task.getProgramField().setNFor(+val);
            task.getProgramField().addText(CommandEnum.FOR);
            task.getCommands().push(CommandEnum.FOR);
            task.getBlock().addCommand(CommandEnum.FOR);
            task.setNumberOfCommands(task.getNumberOfCommands() + 1);
            $('.for').val('0').setAttribute('selected', '');
        }
        task.draw();
    };
}