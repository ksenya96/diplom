/**
 * Created by acer on 28.01.2017.
 */
function Cancel(commandEnum) {
    this.commandEnum = commandEnum;

    this.getCommandEnum = function () {
        return this.commandEnum;
    };

    this.setButtonParameters = function (task) {
        var commands = task.getCommands();
        var programField = task.getProgramField();
        var block = task.getBlock();
        if (commands.length > 0) {
            //если это было ветвление, то удаляем вместе с ветвлением
            if (commands[commands.length - 1] === CommandEnum.JUMP_LEFT
                || commands[commands.length - 1] === CommandEnum.JUMP_UP
                || commands[commands.length - 1] === CommandEnum.JUMP_RIGHT
                || commands[commands.length - 1] === CommandEnum.JUMP_DOWN) {
                commands.pop();
                commands.pop();
                programField.removeString(); //удаляем действие и ветвление
                programField.removeString();
                block.removeCommand();
                block.removeCommand();
                if (task.getNumberOfCommands() - 2 >= 0)
                    task.setNumberOfCommands(task.getNumberOfCommands() - 2);
            }
            //если был оператор
            else if (commands[commands.length - 1] === CommandEnum.IF
                || commands[commands.length - 1] === CommandEnum.FOR
                || commands[commands.length - 1] === CommandEnum.PROCEDURE) {
                if (commands[commands.length - 1] === CommandEnum.IF) {
                    //insert code
                }
                commands.pop();
                programField.removeString();
                block.removeCommand();
                if (task.getNumberOfCommands() > 0)
                    task.setNumberOfCommands(task.getNumberOfCommands() - 1);
            }
            else {
                //циклический оператор
                if (block.getCommands().length > 1 && block.getCommands()[block.getCommands().length - 2] === CommandEnum.FOR) {
                    while (commands[commands.length - 1] !== CommandEnum.FOR)
                        commands.pop();
                    commands.pop();
                    programField.removeString();
                    block.removeCommand();
                    programField.removeString();
                    block.removeCommand();
                    if (task.getNumberOfCommands() - 2 >= 0)
                        task.setNumberOfCommands(task.getNumberOfCommands() - 2);
                }
                else {
                    for (var i = 0; i < SQUARE_SIZE / 5; i++) {
                        commands.pop();
                    }
                    programField.removeString();
                    block.removeCommand();
                    if (task.getNumberOfCommands() > 0)
                        task.setNumberOfCommands(task.getNumberOfCommands() - 1);
                }
            }
        }
        task.draw();
    };


}