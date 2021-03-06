/**
 * Created by acer on 24.01.2017.
 */
function Procedure(commandEnum) {
    this.commandEnum = commandEnum;

    this.getTextForProgramField = function () {
        return 'Proc;';
    };


    this.move = function () {

    };

    this.drawBlock = function (ctxt, point) {
        var x = point.x;
        var y = point.y;


        ctxt.strokeRect(x, y, RECTANGLE_WIDTH, FIGURE_HEIGHT);
        ctxt.fillText(this.commandEnum, x + RECTANGLE_WIDTH / 2 - FONT_SIZE * commandEnum.length / 2.5,
            y + FIGURE_HEIGHT / 2 + FONT_SIZE / 3);

    };

    this.setButtonParameters = function (task) {
        if (task.getNumberOfCommands() < task.getLinesLimit()) {
            task.getProgramField().addText(this.commandEnum);
            task.getBlock().addCommand(this.commandEnum);
            task.getCommands().push(this.commandEnum);
            task.setNumberOfCommands(task.getNumberOfCommands() + 1);
        }
        task.draw();
    };
}