/**
 * Created by acer on 02.01.2017.
 */
function Robot(x0, y0, width, height) {
    this.x0 = x0;
    this.y0 = y0;
    this.width = width;
    this.height = height;
    this.x = x0;
    this.y = y0;
    this.img = new Image();
    /*this.img.onload = function() {

    };*/
    this.img.src = "images/robot.png";

    this.draw = function (ctxt) {
        var robot = this;
        this.img.addEventListener("load", function() {
            // здесь выполняет drawImage функцию
            ctxt.drawImage(robot.img, robot.x, robot.y, width, height);
        }, false);
        ctxt.drawImage(robot.img, robot.x, robot.y, width, height);
    };

    this.setInitialCoords = function () {
        this.x = this.x0;
        this.y = this.y0;
    };

    this.getX = function () {
        return this.x;
    };

    this.getY = function () {
        return this.y;
    };

    this.move = function (command) {
        var factory = new CommandFactory(command);
        var point = factory.move(new Point(this.x, this.y));
        if (point != null) {
            this.x = point.x;
            this.y = point.y;
        }
    };
}