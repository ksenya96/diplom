/**
 * Created by acer on 29.01.2017.
 */
function CancelInProcedure(commandEnum) {
    this.commandEnum = commandEnum;

    this.getCommandEnum = function () {
        return this.commandEnum;
    };

    this.setButtonParameters = function (task) {
        var commandsInProcedure = task.getCommandsInProcedure();
        if (commandsInProcedure.length > 0) {
            for (var i = 0; i < SQUARE_SIZE / 5; i++)
                commandsInProcedure.pop();
            task.getProgramField().removeStringFromProcedure();
            if (task.getNumberOfCommandsInProcedure() > 0)
                task.setNumberOfCommandsInProcedure(task.getNumberOfCommandsInProcedure() - 1);
            if (commandsInProcedure.length == 0)
                task.setNumInProcedure(-1);
        }
    };
}