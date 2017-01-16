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

    }

    this.move = function (point) {
        return this.command.move(point);
    };

    this.getTextForProgramField = function () {
        return this.command.getTextForProgramField();
    };

    this.drawBlock = function (ctxt, point) {
        this.command.drawBlock(ctxt, point);
    }
}