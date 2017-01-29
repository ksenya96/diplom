/**
 * Created by acer on 14.01.2017.
 */
function Point(x, y) {
    this.x = x;
    this.y = y;

    this.getX = function () {
        return this.x;
    };

    this.setX = function (x) {
        this.x = x;
    };

    this.getY = function () {
        return this.y;
    };

    this.setY = function (y) {
        this.y = y;
    };
}