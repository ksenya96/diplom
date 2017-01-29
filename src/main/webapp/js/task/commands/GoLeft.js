/**
 * Created by acer on 14.01.2017.
 */
function GoLeft(commandEnum) {
    this.commandEnum = commandEnum;

    this.getTextForProgramField = function () {
        return this.commandEnum + ';';
    };

    this.drawWay = function (field) {
        var robotWay = field.getRobotWay();
        field.getRobotWay().push(new Point(robotWay[robotWay.length - 1].x - 5, robotWay[robotWay.length - 1].y));
    };

    this.move = function (point) {
        point.x -= 5;
        return point;
    };

    this.drawBlock = function (ctxt, point) {
        var x = point.x;
        var y = point.y;


        ctxt.strokeRect(x, y, RECTANGLE_WIDTH, FIGURE_HEIGHT);
        ctxt.fillText(this.commandEnum, x + RECTANGLE_WIDTH / 2 - FONT_SIZE * commandEnum.length / 2.5,
            y + FIGURE_HEIGHT / 2 + FONT_SIZE / 3);

    };

    this.setButtonParameters = function (task) {
        if (task.getAlgorithm() === CommandEnum.PROCEDURE_ALGORITHMS) {
            //insert code
        }
        else {
            if (task.getNumberOfCommands() < task.getLinesLimit()) {
                var commands = task.getCommands();
                if (commands.length > 0 && commands[commands.length - 1] === CommandEnum.FOR) {
                    task.getProgramField().setForBlock(true);
                    for (var j = 0; j < task.getNFor(); j++)
                        for (var i = 0; i < SQUARE_SIZE / 5; i++)
                            commands.push(this.commandEnum);
                }
                else {
                    for (var i = 0; i < SQUARE_SIZE / 5; i++)
                        commands.push(this.commandEnum);
                }
                task.getProgramField().addText(this.commandEnum);
                task.getBlock().addCommand(this.commandEnum);
                task.setNumberOfCommands(task.getNumberOfCommands() + 1);
            }
        }
        task.draw();
    };
}