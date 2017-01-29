/**
 * Created by acer on 23.01.2017.
 */
function Task(algorithm, ctxt1, ctxt2) {
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



    this.robot = new Robot(this.robotX, this.robotY, this.ROBOT_WIDTH, this.ROBOT_HEIGHT);
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
    if (this.algorithm === CommandEnum.PROCEDURE_ALGORITHMS)
        this.programField.setProcedure(true);
    else
        this.programField.setProcedure(false);
    this.programField.addOnDisplay();
    this.lineLimit = 0;
    this.lineLimitInProcedure = 0;
    this.numberOfCommands = 0;

    this.commands = [];
    this.commandsInProcedure = [];
    this.num = -1;
    this.numForProcedure = -1;

    this.draw = function () {
        this.field.draw(this.ctxt1);
        this.robot.draw(this.ctxt1);
        this.block.draw(this.ctxt2);
    };

    this.makeButtons = function () {
        switch (algorithm) {
            case CommandEnum.LINEAR_ALGORITHMS:
                $('.canvas').first().after('<div class="buttons"><input type="button" value="GoDown" onclick="window.animation(CommandEnum.GO_DOWN);"><br>' +
                    '<input type="button" value="GoUp" onclick="window.animation(CommandEnum.GO_UP);"><br>' +
                    '<input type="button" value="GoLeft" onclick="window.animation(CommandEnum.GO_LEFT);"><br>' +
                    '<input type="button" value="GoRight" onclick="window.animation(CommandEnum.GO_RIGHT);"><br>' +
                    '</div>');
                break;
            case CommandEnum.IF_ALGORITHMS:
                $('.canvas').first().after('<div class="buttons"><input type="button" value="JumpDown" onclick="window.animation(CommandEnum.JUMP_DOWN);"><br>' +
                    '<input type="button" value="JumpUp" onclick="window.animation(CommandEnum.JUMP_UP);"><br>' +
                    '<input type="button" value="JumpLeft" onclick="window.animation(CommandEnum.JUMP_LEFT);"><br>' +
                    '<input type="button" value="JumpRight" onclick="window.animation(CommandEnum.JUMP_RIGHT);"><br>' +
                    '<input type="button" value="if is_wall" onclick="window.animation(CommandEnum.IF)">' +
                    '</div>');
                break;
            case CommandEnum.FOR_ALGORITHMS:
                $('.canvas').first().after('<div class="buttons"><input type="button" value="GoDown" onclick="window.animation(CommandEnum.GO_DOWN);"><br>' +
                    '<input type="button" value="GoUp" onclick="window.animation(CommandEnum.GO_UP);"><br>' +
                    '<input type="button" value="GoLeft" onclick="window.animation(CommandEnum.GO_LEFT);"><br>' +
                    '<input type="button" value="GoRight" onclick="window.animation(CommandEnum.GO_RIGHT);"><br>' +
                    '<select class="for" onchange="window.animation(CommandEnum.FOR)">' +
                    steps +
                    '</select>' +
                    '</div>');
                break;
            case CommandEnum.PROCEDURE_ALGORITHMS:
                break;
        }
    };






}