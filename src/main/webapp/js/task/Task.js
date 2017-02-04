/**
 * Created by acer on 23.01.2017.
 */
function Task(algorithm, ctxt1, ctxt2, beginCellOfRobot, cells, linesLimit) {
    this.fieldX = 15;
    this.fieldY = 15;
    this.FIELD_WIDTH = 6;
    this.FIELD_HEIGHT = 6;
    this.field = new Filed(this.fieldX, this.fieldY, this.FIELD_WIDTH, this.FIELD_HEIGHT);

    this.programFiledWidth = 25;
    this.programFiledHeight = 34;
    this.robotX = this.fieldX;
    this.robotY = 0;
    this.ROBOT_WIDTH = SQUARE_SIZE;
    this.ROBOT_HEIGHT = SQUARE_SIZE + 10;
    this.blockX = 15;
    this.blockY = 15;
    this.blockWidth = 220;
    this.blockHeight = this.FIELD_WIDTH * SQUARE_SIZE;
    this.beginCellOfRobot = beginCellOfRobot;



    this.robot = new Robot(this.robotX + beginCellOfRobot.x * SQUARE_SIZE, this.robotY + beginCellOfRobot.y * SQUARE_SIZE, this.ROBOT_WIDTH, this.ROBOT_HEIGHT);
    this.field.setRobotCoords(new Point(this.fieldX + this.beginCellOfRobot.x * SQUARE_SIZE,
        this.fieldY + this.beginCellOfRobot.y * SQUARE_SIZE));
    this.field.setNecessaryCells(cells);
    this.programField = new ProgramField(this.programFiledWidth, this.programFiledHeight);
    this.block = new Block(this.blockX, this.blockY, this.blockWidth, this.blockHeight);

    this.nFor = 0;
    this.algorithm = algorithm;
    this.ctxt1 = ctxt1;
    this.ctxt2 = ctxt2;
    if (this.algorithm === CommandEnum.IF_ALGORITHMS)
        this.delay = 500;
    else
        this.delay = 50;
    this.programField.addOnDisplay();
    if (this.algorithm === CommandEnum.PROCEDURE_ALGORITHMS)
        this.programField.setProcedure(true);
    else
        this.programField.setProcedure(false);
    this.linesLimit = linesLimit;
    this.linesLimitInProcedure = 0;
    this.numberOfCommands = 0;
    this.numberOfCommandsInProcedure = 0;

    this.commands = [];
    this.commandsInProcedure = [];
    this.num = -1;
    this.numInProcedure = -1;

    this.timer = null;
    this.timerIsOn = false;

    this.draw = function () {

        this.field.draw(this.ctxt1);
        this.robot.draw(this.ctxt1);
        this.block.draw(this.ctxt2);
    };

    this.makeButtons = function () {
        var task = this;
        switch (algorithm) {
            case CommandEnum.LINEAR_ALGORITHMS:
                $('.canvas').first().after('<div class="buttons">' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_DOWN).setButtonParameters(task);"><img src="images/GoDown.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_UP).setButtonParameters(task);"><img src="images/GoUp.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_LEFT).setButtonParameters(task);"><img src="images/GoLeft.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_RIGHT).setButtonParameters(task);"><img src="images/GoRight.png"></button><br>' +
                    '</div>');
                break;
            case CommandEnum.IF_ALGORITHMS:
                $('.canvas').first().after('<div class="buttons">' +
                    '<button onclick="new CommandFactory(CommandEnum.JUMP_DOWN).setButtonParameters(task);" disabled>JumpDown</button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.JUMP_UP).setButtonParameters(task);" disabled>JumpUp</button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.JUMP_LEFT).setButtonParameters(task);" disabled>JumpLeft</button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.JUMP_RIGHT).setButtonParameters(task);" disabled>JumpRight</button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.IF).setButtonParameters(task);">if is_wall</button>' +
                    '</div>');
                break;
            case CommandEnum.FOR_ALGORITHMS:
                var steps = '<option value="0">n := </option>';
                for (var i = 2; i <= 5; i++)
                    steps += '<option value="' + i + '">n := ' + i + '</option>';
                $('.canvas').first().after('<div class="buttons">' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_DOWN).setButtonParameters(task);"><img src="images/GoDown.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_UP).setButtonParameters(task);"><img src="images/GoUp.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_LEFT).setButtonParameters(task);"><img src="images/GoLeft.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_RIGHT).setButtonParameters(task);"><img src="images/GoRight.png"></button><br>' +
                    '<select class="for" onchange="new CommandFactory(CommandEnum.FOR).setButtonParameters(task);">' +
                    steps +
                    '</select>' +
                    '</div>');
                break;
            case CommandEnum.PROCEDURE_ALGORITHMS:
                $('.canvas').first().after('<div class="buttons">' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_DOWN).setButtonParameters(task);"><img src="images/GoDown.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_UP).setButtonParameters(task);"><img src="images/GoUp.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_LEFT).setButtonParameters(task);"><img src="images/GoLeft.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.GO_RIGHT).setButtonParameters(task);"><img src="images/GoRight.png"></button><br>' +
                    '<button onclick="new CommandFactory(CommandEnum.PROCEDURE).setButtonParameters(task);">Proc</button><br>' +
                    '</div>');
                break;
        }

        var appendButtons = '<div id="functional_buttons"><button onclick="new CommandFactory(CommandEnum.START).setButtonParameters(task);">Пуск</button>' +
            '<button onclick="new CommandFactory(CommandEnum.CANCEL).setButtonParameters(task);">Отмена</button>';
        if (this.algorithm === CommandEnum.PROCEDURE_ALGORITHMS)
            appendButtons += '<button onclick="new CommandFactory(CommandEnum.CANCEL_IN_PROCEDURE).setButtonParameters(task);">Отмена в Proc</button>';
        appendButtons += '<button onclick="new CommandFactory(CommandEnum.RETURN).setButtonParameters(task);">В начало</button>' +
            '<button>Помощь</button></div>';
        appendButtons += '<div id="message"></div>';
        $('#textarea').after(appendButtons);

    };

    window.animation = function (task) {
        $('#message').empty();
        if (task.num < task.commands.length - 1 && task.robot.getX() + task.ROBOT_WIDTH <= task.fieldX + SQUARE_SIZE * task.FIELD_WIDTH
        && task.robot.getY() + task.ROBOT_HEIGHT <= task.fieldY + SQUARE_SIZE * task.FIELD_HEIGHT
        && task.robot.getX() >= task.fieldX
        && task.robot.getY() >= task.robotY) {
            if (task.algorithm !== CommandEnum.PROCEDURE_ALGORITHMS) {
                task.num++;
                task.field.drawWay(task.commands[task.num]);
                task.robot.move(task.commands[task.num]);
                task.getField().draw(task.getCtxt1());
                task.getRobot().draw(task.getCtxt1());
            }
            else {
                if (task.numInProcedure < task.commandsInProcedure.length - 1) {
                    task.numInProcedure++;
                    task.field.drawWay(task.commandsInProcedure[task.numInProcedure]);
                    task.robot.move(task.commandsInProcedure[task.numInProcedure]);
                    task.getField().draw(task.getCtxt1());
                    task.getRobot().draw(task.getCtxt1());
                }
                else {
                    task.numInProcedure = -1;
                    task.num++;
                }
            }
        }
        else {
            if ((task.num < task.commands.length) && (task.robot.getX() + task.ROBOT_WIDTH > task.fieldX + SQUARE_SIZE * task.FIELD_WIDTH
                || task.robot.getY() + task.ROBOT_HEIGHT > task.fieldY + SQUARE_SIZE * task.FIELD_HEIGHT
                || task.robot.getX() < task.fieldX
                || task.robot.getY() < task.robotY)) {

                var pos = task.num;
                if (task.algorithm === CommandEnum.PROCEDURE_ALGORITHMS)
                    pos++;
                if (!(task.commands[pos] === CommandEnum.JUMP_LEFT || task.commands[pos] === CommandEnum.JUMP_UP
                    || task.commands[pos] === CommandEnum.JUMP_RIGHT || task.commands[pos] === CommandEnum.JUMP_DOWN
                    || task.commands[pos] === CommandEnum.PROCEDURE)) {
                    pos /= (SQUARE_SIZE / 5);
                }
                $('#message').append('Ошибка! Выход за границы поля. Строка ' + (pos + 2));
            }
            else if (task.field.isAllCellsCrossed()) {
                $('#message').append('Молодец!');
                taskIsDone();
            }
            task.stopTimer();
        }

        if (task.timerIsOn)
            setTimeout(function() {animation(task)}, task.delay);

    };



    this.startTimer = function () {
        if (!this.timerIsOn) {
            this.timerIsOn = true;
            this.timer = setTimeout(animation(this), this.delay);
        }
    };

    this.stopTimer = function () {
        if (this.timer != null) {
            clearTimeout(this.timer);
            this.timerIsOn = false;
        }

    };

    this.getAlgorithm = function () {
        return this.algorithm;
    };

    this.getNumberOfCommands = function () {
        return this.numberOfCommands;
    };

    this.getLinesLimit = function () {
        return this.linesLimit;
    };

    this.setLinesLimit = function (linesLimit) {
        this.linesLimit = linesLimit;
    };

    this.getCommands = function () {
        return this.commands;
    };

    this.getProgramField = function () {
        return this.programField;
    };

    this.getBlock = function () {
        return this.block;
    };

    this.setNumberOfCommands = function (numberOfCommands) {
        this.numberOfCommands = numberOfCommands;
    };

    this.getNFor = function () {
        return this.nFor;
    };

    this.setNFor = function (nFor) {
        this.nFor = nFor;
    };

    this.getField = function () {
        return this.field;
    };

    this.getRobot = function () {
        return this.robot;
    };

    this.getCtxt1 = function () {
        return this.ctxt1;
    };

    this.getCtxt2 = function () {
        return this.ctxt2;
    };

    this.setNum = function (num) {
        this.num = num;
    };

    this.setNumInProcedure = function (numInProcedure) {
        this.numInProcedure = numInProcedure;
    };

    this.getNumberOfCommandsInProcedure = function () {
        return this.numberOfCommandsInProcedure;
    };

    this.setNumberOfCommandsInProcedure = function (numberOfCommandsInProcedure) {
        this.numberOfCommandsInProcedure = numberOfCommandsInProcedure;
    };

    this.getLinesLimitInProcedure = function () {
        return this.linesLimitInProcedure;
    };

    this.setLinesLimitInProcedure = function (linesLimitInProcedure) {
        this.linesLimitInProcedure = linesLimitInProcedure;
    };

    this.getCommandsInProcedure = function () {
        return this.commandsInProcedure;
    };


}