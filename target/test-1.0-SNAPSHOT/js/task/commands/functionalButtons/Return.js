/**
 * Created by acer on 28.01.2017.
 */
function Return(commandEnum) {
    this.commandEnum = commandEnum;

    this.getCommandEnum = function () {
        return this.commandEnum;
    };

    this.setButtonParameters = function (task) {
        task.getField().clearWay();
        task.getRobot().setInitialCoords();
        task.setNum(-1);
        task.setNumInProcedure(-1);
        task.draw();
    };


}