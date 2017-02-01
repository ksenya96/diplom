/**
 * Created by acer on 28.01.2017.
 */
function Start(commandEnum) {
    this.commandEnum = commandEnum;

    this.getCommandEnum = function () {
        return this.commandEnum;
    };

    this.setButtonParameters = function (task) {
        task.startTimer();
    };


}