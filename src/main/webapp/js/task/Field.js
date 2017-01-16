/**
 * Created by acer on 02.01.2017.
 */
function Filed(x0, y0, width, height) {
    this.SQUARE_SIZE = 85;
    this.x0 = x0;
    this.y0 = y0;
    this.width = width;
    this.height = height;
    this.robotWay = [];
    this.necessaryCells = [];
    this.barrierCells = [];
    this.robotCoords = null;

    this.draw = function(ctxt) {
        var x = this.x0;
        var y = this.y0;
        var border = 15;
        ctxt.strokeStyle = "rgb(139, 9, 12)";
        ctxt.fillStyle = "rgb(139, 9, 12)";
        ctxt.fillRect(x0 - border, y0 - border, width * this.SQUARE_SIZE + border * 2, height * this.SQUARE_SIZE + border * 2);
        ctxt.lineWidth = 2;

        for (var i = 0; i < width; i++) {
            for (var j = 0; j < height; j++) {
                ctxt.strokeStyle = "rgb(250, 250, 184)";
                ctxt.fillStyle = "rgb(250, 250, 184)";
                ctxt.fillRect(x, y, this.SQUARE_SIZE, this.SQUARE_SIZE);
                ctxt.strokeStyle = "rgb(139, 9, 12)";
                ctxt.strokeStyle = "rgb(139, 9, 12)";
                ctxt.strokeRect(x, y, this.SQUARE_SIZE, this.SQUARE_SIZE);
                x += this.SQUARE_SIZE;
            }
            y += this.SQUARE_SIZE;
            x = x0;
        }

        /*this.paintCells(ctxt);
        ctxt.fillStyle = "rgb(255, 163, 2)";
        ctxt.fillRect(this.robotCoords.x, this.robotCoords.y, this.SQUARE_SIZE, this.SQUARE_SIZE);
        ctxt.strokeStyle = "rgb(139, 9, 12)";
        ctxt.strokeRect(this.robotCoords.x, this.robotCoords.y, this.SQUARE_SIZE, this.SQUARE_SIZE);

        ctxt.strokeStyle = "black";
        for (var i = 1; i < this.robotWay.length; i += 2) {
            draw(this.robotWay[i - 1].x, this.robotWay[i - 1].y, this.robotWay[i].x, this.robotWay[i].y);
        }*/
    };

    this.drawWay = function (command) {

    };

    this.clearWay = function () {
        this.robotWay = [];
        this.robotWay.push(new Point(this.robotCoords.x + this.SQUARE_SIZE / 2, this.robotCoords.y + this.SQUARE_SIZE / 2));
    };

    this.paintCells = function(ctxt) {
        var x, y;
        for (var i = 0; i < this.necessaryCells.length; i++) {
            x = this.x0 + this.necessaryCells[i].x * this.SQUARE_SIZE;
            y = this.y0 + this.necessaryCells[i].y * this.SQUARE_SIZE;
            ctxt.fillStyle = "rgb(255, 255, 0)";
            ctxt.fillRect(x, y, this.SQUARE_SIZE, this.SQUARE_SIZE);
            ctxt.strokeStyle = "rgb(139, 9, 12)";
            ctxt.strokeRect(x, y, this.SQUARE_SIZE, this.SQUARE_SIZE);
        }

        if (this.barrierCells != null) {
            for (var i = 0; i < this.barrierCells.length; i++) {
                x = this.x0 + this.barrierCells[i].x * this.SQUARE_SIZE;
                y = this.y0 + this.barrierCells[i].y * this.SQUARE_SIZE;
                ctxt.fillStyle = "rgb(0, 0, 0)";
                ctxt.fillRect(x, y, this.SQUARE_SIZE, this.SQUARE_SIZE);
                ctxt.fillStyle = "rgb(139, 9, 12)";
                ctxt.fillRect(x, y, this.SQUARE_SIZE, this.SQUARE_SIZE);
            }
        }
    };

    this.setNecessaryCells = function (necessaryCells) {
        this.necessaryCells = necessaryCells;
    };

    this.setBarrierCells = function (barrierCells) {
        this.barrierCells = barrierCells;
    };

    this.isAllCellsCrossed = function () {
        var k = 0;
        for (var i = 0; i < this.necessaryCells.length; i++) {
            for (var j = 0; j < this.robotWay.length; j++)
                if (this.robotWay[j].x == (this.x0 + this.necessaryCells[i].x * this.SQUARE_SIZE + this.SQUARE_SIZE / 2) &&
                    this.robotWay[j].y == (this.y0 + this.necessaryCells[i].y * this.SQUARE_SIZE + this.SQUARE_SIZE / 2))
                    k++;
        }
        return k >= this.necessaryCells.length;
    };

    this.setRobotCoords = function (robotCoords) {
        this.robotCoords = robotCoords;
        this.robotWay.push(new Point(robotCoords.x + this.SQUARE_SIZE / 2, robotCoords.y + this.SQUARE_SIZE / 2));
    };

    this.getRobotWay = function () {
        return this.robotWay;
    }
}