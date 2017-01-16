/**
 * Created by acer on 03.01.2017.
 */
function Block(x0, y0, width, height) {
    this.x0 = x0;
    this.y0 = y0;
    this.width = width;
    this.height = height;
    this.BEGIN = 'begin';
    this.END = 'end.';
    window.OVAL_WIDTH = width / 2;
    window.RECTANGLE_WIDTH = width / 2;
    window.FIGURE_HEIGHT = 25;
    window.BIG_ARROW_LENGTH = 15;
    this.commands = [];
    window.SMALL_ARROW_LENGTH = 5;
    window.ALPHA = Math.PI / 6;
    this.nFor = 0;
    window.FONT_SIZE = 16;

    this.addCommand = function (command) {
        this.commands.push(command);
    };

    this.removeCommand = function () {
        var length = this.commands.length;
        if (length != 0)
            delete this.commands[length - 1];
    };

    this.draw = function(ctxt) {
        ctxt.strokeStyle = "rgb(139, 9, 12)";
        ctxt.fillStyle = "rgb(139, 9, 12)";
        ctxt.fillRect(this.x0 - 5, this.y0 - 5, this.width + 10, this.height + 10);

        ctxt.strokeStyle = "white";
        ctxt.fillStyle = "white";
        ctxt.fillRect(this.x0, this.y0, this.width, this.height);

        var y = y0;
        ctxt.strokeStyle = "black";
        ctxt.fillStyle = "black";
        ctxt.font = "bold " + this.FONT_SIZE + "pt Courier New";
        var x = this.x0 + this.width / 2 - this.OVAL_WIDTH / 2;

        //слово "begin"
        y += 5;
        drawLine(x, y, x + this.OVAL_WIDTH, y, ctxt);
        drawLine(x, y + this.FIGURE_HEIGHT, x + this.OVAL_WIDTH, y + this.FIGURE_HEIGHT, ctxt);
        drawArcLeft(x, y + this.FIGURE_HEIGHT / 2, this.FIGURE_HEIGHT, this.FIGURE_HEIGHT, ctxt);
        drawArcRight(x + this.OVAL_WIDTH, y + this.FIGURE_HEIGHT / 2, this.FIGURE_HEIGHT, this.FIGURE_HEIGHT, ctxt);
        ctxt.fillText(this.BEGIN, x + this.OVAL_WIDTH / 2 - this.FONT_SIZE * this.BEGIN.length / 3, y + this.FIGURE_HEIGHT / 2 + 16 / 3);

        //стрелочка
        drawLine(this.x0 + this.width / 2, y + this.FIGURE_HEIGHT, this.x0 + this.width / 2, y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH, ctxt);
        drawLine(this.x0 + this.width / 2, y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH, this.x0 + this.width / 2 - this.SMALL_ARROW_LENGTH / Math.sin(this.ALPHA), y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH - this.SMALL_ARROW_LENGTH / Math.cos(this.ALPHA), ctxt);
        drawLine(this.x0 + this.width / 2, y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH, this.x0 + this.width / 2 + this.SMALL_ARROW_LENGTH / Math.sin(this.ALPHA), y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH - this.SMALL_ARROW_LENGTH / Math.cos(this.ALPHA), ctxt);

        //введенные команды
        x = this.x0 + this.width / 2 - this.RECTANGLE_WIDTH / 2;
        for (var i = 0; i < this.commands.length; i++) {
            y += this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH;
            var command = this.commands[i];
            var factory = new CommandFactory(command);
            if (command === CommandEnum.FOR) {
                //insert code
            }
            factory.drawBlock(ctxt, new Point(x, y));

            if (command === CommandEnum.JUMP_DOWN || command === CommandEnum.JUMP_LEFT ||
                command === CommandEnum.JUMP_UP || command === CommandEnum.JUMP_RIGHT)
                y += 5;

            //отрисовка стрелок для циклического блока
            if (i - 1 >= 0 && this.commands[i - 1] === CommandEnum.FOR) {
                drawLine(x, y + this.FIGURE_HEIGHT / 2, x - 20, y + this.FIGURE_HEIGHT / 2, ctxt);
                drawLine(x - 20, y + this.FIGURE_HEIGHT / 2, x - 20, y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH, ctxt);
                drawLine(x - 20, y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH, x, y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH, ctxt);
                //стрелочка
                drawLine(x, y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH, x - this.SMALL_ARROW_LENGTH / Math.cos(this.ALPHA), y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH - this.SMALL_ARROW_LENGTH / Math.sin(this.ALPHA), ctxt);
                drawLine(x, y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH, x - this.SMALL_ARROW_LENGTH / Math.cos(this.ALPHA), y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH + this.SMALL_ARROW_LENGTH / Math.sin(this.ALPHA), ctxt);

                drawLine(x + this.RECTANGLE_WIDTH, y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH, x + this.RECTANGLE_WIDTH + 20, y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH, ctxt);
                drawLine(x + this.RECTANGLE_WIDTH + 20, y - this.FIGURE_HEIGHT / 2 - this.BIG_ARROW_LENGTH, x + this.RECTANGLE_WIDTH + 20, y + this.FIGURE_HEIGHT + 5, ctxt);
                drawLine(x + this.RECTANGLE_WIDTH + 20, y - this.FIGURE_HEIGHT + 5, x + this.RECTANGLE_WIDTH / 2, y + this.FIGURE_HEIGHT + 5, ctxt);
                y += 5;
            }

            //стрелочка
            if (this.commands[i] != CommandEnum.IF) {
                drawLine(this.x0 + this.width / 2, y + this.FIGURE_HEIGHT, this.x0 + this.width / 2, y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH, ctxt);
                drawLine(this.x0 + this.width / 2, y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH, this.x0 + this.width / 2 - this.SMALL_ARROW_LENGTH / Math.sin(this.ALPHA), y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH - this.SMALL_ARROW_LENGTH / Math.cos(this.ALPHA), ctxt);
                drawLine(this.x0 + this.width / 2, y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH, this.x0 + this.width / 2 + this.SMALL_ARROW_LENGTH / Math.sin(this.ALPHA), y + this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH - this.SMALL_ARROW_LENGTH / Math.cos(this.ALPHA), ctxt);
            }
        }

        //слово "end"
        x = this.x0 + this.width / 2 - this.OVAL_WIDTH / 2;
        y += this.FIGURE_HEIGHT + this.BIG_ARROW_LENGTH;
        drawLine(x, y, x + this.OVAL_WIDTH, y, ctxt);
        drawLine(x, y + this.FIGURE_HEIGHT, x + this.OVAL_WIDTH, y + this.FIGURE_HEIGHT, ctxt);
        drawArcLeft(x, y + this.FIGURE_HEIGHT / 2, this.FIGURE_HEIGHT, this.FIGURE_HEIGHT, ctxt);
        drawArcRight(x + this.OVAL_WIDTH, y + this.FIGURE_HEIGHT / 2, this.FIGURE_HEIGHT, this.FIGURE_HEIGHT, ctxt);
        ctxt.fillText(this.END, x + this.OVAL_WIDTH / 2 - this.FONT_SIZE * this.END.length / 3, y + this.FIGURE_HEIGHT / 2 + 16 / 3);


    };


    this.setNFor = function (n) {
        this.nFor = n;
    };

    this.getCommands = function () {
        return this.commands;
    };



}