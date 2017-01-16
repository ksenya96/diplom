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
<div class="canvas"><canvas height="530" width="220" id="canvas2">Обновите браузер</canvas></div>
<script type="text/javascript" src="js/task/Field.js"></script>
<script type="text/javascript" src="js/task/Robot.js"></script>
<script type="text/javascript" src="js/task/Block.js"></script>
<script type="text/javascript" src="js/task/ProgramField.js"></script>
<script>
    var canvas1 = document.getElementById("canvas1"),
            ctxt1 = canvas1.getContext('2d');
    var canvas2 = document.getElementById("canvas2"),
            ctxt2 = canvas2.getContext('2d');
    var field = new Filed(15, 15, 6, 6);

    var robot = new Robot(15, 0, field.SQUARE_SIZE, field.SQUARE_SIZE + 10);

    field.draw(ctxt1);
    robot.draw(ctxt1);

    var block = new Block(15, 15, 180, 6 * 85);
    block.draw(ctxt2);


    /*function animation() {
     ctxt.clearRect(0, 0, canvas.width, canvas.height);
     field.draw(ctxt);
     robot.draw(ctxt);
     robot.move();
     ctxt.restore();
     }*/

    //setInterval(animation, 33);

    var programField = new ProgramField(34, 10);
    programField.addOnCanvas();

</script>

</body>
</html>