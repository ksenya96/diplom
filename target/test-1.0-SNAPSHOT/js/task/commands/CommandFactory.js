/**
 * Created by acer on 14.01.2017.
 */
function CommandFactory(commandEnum) {
    switch (commandEnum) {
        case CommandEnum.GO_DOWN:
            this.command = new GoDown(commandEnum);
            break;
        case CommandEnum.GO_RIGHT:
            this.command = new GoRight(commandEnum);
            break;
        case CommandEnum.GO_UP:
            this.command = new GoUp(commandEnum);
            break;
        case CommandEnum.GO_LEFT:
            this.command = new GoLeft(commandEnum);
            break;
        case CommandEnum.JUMP_DOWN:
            this.command = new JumpDown(commandEnum);
            break;
        case CommandEnum.JUMP_LEFT:
            this.command = new JumpLeft(commandEnum);
            break;
        case CommandEnum.JUMP_RIGHT:
            this.command = new JumpRight(commandEnum);
            break;
        case CommandEnum.JUMP_UP:
            this.command = new JumpUp(commandEnum);
            break;
        case CommandEnum.IF:
            this.command = new If(commandEnum);
            break;
        case CommandEnum.FOR:
            this.command = new For(commandEnum);
            break;
        case CommandEnum.PROCEDURE:
            this.command = new Procedure(commandEnum);
            break;
        case CommandEnum.START:
            this.command = new Start(commandEnum);
            break;
        case CommandEnum.RETURN:
            this.command = new Return(commandEnum);
            break;
        case CommandEnum.CANCEL:
            this.command = new Cancel(commandEnum);
            break;
        case CommandEnum.CANCEL_IN_PROCEDURE:
            this.command = new CancelInProcedure(commandEnum);
            break;
    }

    this.move = function (point) {
        return this.command.move(point);
    };

    this.getTextForProgramField = function () {
        return this.command.getTextForProgramField();
    };

    this.drawBlock = function (ctxt, point) {
        this.command.drawBlock(ctxt, point);
    };

    this.setButtonParameters = function (task) {
        this.command.setButtonParameters(task);
    };

    this.getCommand = function () {
        return this.command;
    };

    this.drawWay = function (field) {
        this.command.drawWay(field);
    }
}