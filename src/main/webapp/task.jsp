<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 23.08.2016
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${task.title}</title>
    <link rel="stylesheet" type="text/css" href="css/index/star.css">
</head>
<body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>
<div class="canvas"><canvas height="600" width="600" id="canvas1">Обновите браузер</canvas></div>
<div class="canvas"><canvas height="530" width="250" id="canvas2">Обновите браузер</canvas></div>
<script type="text/javascript" src="js/task/Field.js"></script>
<script type="text/javascript" src="js/task/Robot.js"></script>
<script type="text/javascript" src="js/task/Block.js"></script>
<script type="text/javascript" src="js/task/ProgramField.js"></script>
<script type="text/javascript" src="js/task/commands/CommandEnum.js"></script>
<script type="text/javascript" src="js/task/commands/CommandFactory.js"></script>
<script type="text/javascript" src="js/task/commands/GoDown.js"></script>
<script type="text/javascript" src="js/task/commands/GoRight.js"></script>
<script type="text/javascript" src="js/task/commands/GoUp.js"></script>
<script type="text/javascript" src="js/task/commands/GoLeft.js"></script>
<script type="text/javascript" src="js/task/commands/JumpDown.js"></script>
<script type="text/javascript" src="js/task/commands/JumpRight.js"></script>
<script type="text/javascript" src="js/task/commands/JumpUp.js"></script>
<script type="text/javascript" src="js/task/commands/JumpLeft.js"></script>
<script type="text/javascript" src="js/task/commands/If.js"></script>
<script type="text/javascript" src="js/task/commands/For.js"></script>
<script type="text/javascript" src="js/task/Point.js"></script>
<script type="text/javascript" src="js/task/DrawLibrary.js"></script>
<script type="text/javascript" src="js/task/Task.js"></script>




<script>
    var canvas1 = document.getElementById("canvas1"),
            ctxt1 = canvas1.getContext('2d');
    var canvas2 = document.getElementById("canvas2"),
            ctxt2 = canvas2.getContext('2d');
    /*var fieldX = 15;

     var field = new Filed(15, 15, 6, 6);

     var robot = new Robot(15, 0, field.SQUARE_SIZE, field.SQUARE_SIZE + 10);
     field.setRobotCoords(15, 0);

     field.draw(ctxt1);
     robot.draw(ctxt1);

     var block = new Block(15, 15, 220, 6 * 85);
     block.draw(ctxt2);


     function animation(command) {
     if (command === CommandEnum.FOR) {
     var val = $('.for option:selected').attr('value').trim();
     var re = new RegExp('\\d');
     if (re.test(val)) {
     block.setNFor(+val);
     programField.setNFor(+val);
     }
     }
     ctxt1.clearRect(0, 0, canvas1.width, canvas1.height);
     robot.move(command);
     field.draw(ctxt1);
     robot.draw(ctxt1);
     block.addCommand(command);
     block.draw(ctxt2);
     programField.addText(command);
     ctxt1.restore();
     }



     //setInterval(animation, 33);

     var programField = new ProgramField(25, 34);
     programField.addOnDisplay();


     var steps = '<option value="0">n := </option>';
     for (var i = 2; i <= 5; i++)
     steps += '<option value="' + i + '">n := ' + i + '</option>';

     $('.canvas').first().after('<div class="buttons"><input type="button" value="GoDown" onclick="animation(CommandEnum.GO_DOWN);"><br>' +
     '<input type="button" value="GoUp" onclick="animation(CommandEnum.GO_UP);"><br>' +
     '<input type="button" value="GoLeft" onclick="animation(CommandEnum.GO_LEFT);"><br>' +
     '<input type="button" value="GoRight" onclick="animation(CommandEnum.GO_RIGHT);"><br>' +
     '<select class="for" onchange="animation(CommandEnum.FOR)">' +
     steps +
     '</select>' +
     '</div>');
     */

    /*$('.canvas').first().after('<div class="buttons"><input type="button" value="JumpDown" onclick="animation(CommandEnum.JUMP_DOWN);"><br>' +
     '<input type="button" value="JumpUp" onclick="animation(CommandEnum.JUMP_UP);"><br>' +
     '<input type="button" value="JumpLeft" onclick="animation(CommandEnum.JUMP_LEFT);"><br>' +
     '<input type="button" value="JumpRight" onclick="animation(CommandEnum.JUMP_RIGHT);"><br>' +
     '<input type="button" value="if is_wall" onclick="animation(CommandEnum.IF)">' +
     '</div>');*/

    var task = new Task(CommandEnum.LINEAR_ALGORITHMS, ctxt1, ctxt2);
    task.draw();
    task.makeButtons();


    window.animation = function (command) {
        if (command === CommandEnum.FOR) {
            var val = $('.for option:selected').attr('value').trim();
            var re = new RegExp('\\d');
            if (re.test(val)) {
                task.block.setNFor(+val);
                task.programField.setNFor(+val);
            }
        }
        task.ctxt1.clearRect(0, 0, canvas1.width, canvas1.height);
        task.ctxt2.clearRect(0, 0, canvas2.width, canvas2.height);
        task.robot.move(command);
        task.field.draw(this.ctxt1);
        task.robot.draw(this.ctxt1);
        task.block.addCommand(command);
        task.block.draw(this.ctxt2);
        task.programField.addText(command);
        task.ctxt1.restore();
        task.ctxt2.restore();
    };
</script>

</body>
</html>