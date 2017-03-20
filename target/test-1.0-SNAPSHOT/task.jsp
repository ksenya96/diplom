<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<h2>${task.title}</h2>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script>


<c:if test="${task.type == 'ROBOT'}">
    <form method="post" action="/task">
        <input type="submit" id="is_done" value="Нажмите, когда задание будет выполнено" disabled>
    </form>
    <div class="canvas"><canvas height="600" width="600" id="canvas1">Обновите браузер</canvas></div>
    <div class="canvas"><canvas height="530" width="250" id="canvas2">Обновите браузер</canvas></div>
    <script type="text/javascript" src="js/task/Field.js"></script>
    <script type="text/javascript" src="js/task/Robot.js"></script>
    <script type="text/javascript" src="js/task/Block.js"></script>
    <script type="text/javascript" src="js/task/ProgramField.js"></script>
    <script type="text/javascript" src="js/task/commands/CommandEnum.js"></script>
    <script type="text/javascript" src="js/task/commands/CommandFactory.js"></script>
    <script type="text/javascript" src="js/task/commands/go/Go.js"></script>
    <script type="text/javascript" src="js/task/commands/go/GoDown.js"></script>
    <script type="text/javascript" src="js/task/commands/go/GoRight.js"></script>
    <script type="text/javascript" src="js/task/commands/go/GoUp.js"></script>
    <script type="text/javascript" src="js/task/commands/go/GoLeft.js"></script>
    <script type="text/javascript" src="js/task/commands/jump/Jump.js"></script>
    <script type="text/javascript" src="js/task/commands/jump/JumpDown.js"></script>
    <script type="text/javascript" src="js/task/commands/jump/JumpRight.js"></script>
    <script type="text/javascript" src="js/task/commands/jump/JumpUp.js"></script>
    <script type="text/javascript" src="js/task/commands/jump/JumpLeft.js"></script>
    <script type="text/javascript" src="js/task/commands/operators/If.js"></script>
    <script type="text/javascript" src="js/task/commands/operators/For.js"></script>
    <script type="text/javascript" src="js/task/Point.js"></script>
    <script type="text/javascript" src="js/task/DrawLibrary.js"></script>
    <script type="text/javascript" src="js/task/Task.js"></script>
    <script type="text/javascript" src="js/task/commands/operators/Procedure.js"></script>
    <script type="text/javascript" src="js/task/commands/functionalButtons/Start.js"></script>
    <script type="text/javascript" src="js/task/commands/functionalButtons/Return.js"></script>
    <script type="text/javascript" src="js/task/commands/functionalButtons/Cancel.js"></script>
    <script type="text/javascript" src="js/task/commands/functionalButtons/CancelInProcedure.js"></script>



    <script>
        var canvas1 = document.getElementById("canvas1"),
                ctxt1 = canvas1.getContext('2d');
        var canvas2 = document.getElementById("canvas2"),
                ctxt2 = canvas2.getContext('2d');

        var theme;
        switch ('${task.theme.title}') {
            case CommandEnum.LINEAR_ALGORITHMS:
                theme = CommandEnum.LINEAR_ALGORITHMS;
                break;
            case CommandEnum.IF_ALGORITHMS:
                theme = CommandEnum.IF_ALGORITHMS;
                break;
            case CommandEnum.FOR_ALGORITHMS:
                theme = CommandEnum.FOR_ALGORITHMS;
                break;
            case CommandEnum.PROCEDURE_ALGORITHMS:
                theme = CommandEnum.PROCEDURE_ALGORITHMS;
                break;
        }


        var cells = [];
        var beginCell;
        var numberOfSteps;
        var string = '${task_content}';
        var paar = string.split('|');
        paar[0] = paar[0].trim();
        var nums = paar[0].split(' ');
        beginCell = new Point(+nums[0], +nums[1]);
        var i = 1;
        paar[i] = paar[i].trim();
        nums = paar[i].split(' ');
        while (nums.length > 1) {
            cells.push(new Point(+nums[0], +nums[1]));
            i++;
            paar[i] = paar[i].trim();
            nums = paar[i].split(' ');
        }
        numberOfSteps = +paar[i];

        var task = new Task(theme, ctxt1, ctxt2, beginCell, cells, numberOfSteps);
        if (theme === CommandEnum.PROCEDURE_ALGORITHMS) {
            var numberOfStepsInProcedure = +paar[i + 1];
            task.setLinesLimitInProcedure(numberOfStepsInProcedure);
        }
        if (theme === CommandEnum.IF_ALGORITHMS) {
            var barrier = [];
            i++;
            while (i < paar.length) {
                paar[i] = paar[i].trim();
                nums = paar[i].split(' ');
                barrier.push(new Point(+nums[0], +nums[1]));
                i++;
            }
            task.getField().setBarrierCells(barrier);
        }
        //var cells = [new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(4, 0)];
        //var barrier = [new Point(2, 1), new Point(3, 2), new Point(3, 4), new Point(4, 5)];
        //var task = new Task(CommandEnum.LINEAR_ALGORITHMS, ctxt1, ctxt2, new Point(1, 1), cells, 4);

        task.draw();
        task.makeButtons();


    </script>
</c:if>

<c:if test="${task.type == 'PROGRAM'}">
    <div>
        <table class="decoration">
            <tr>
                <td>${task_content}</td>
            </tr>
            <tr align="center">
                <td>
                    <form action="/task" method="post" enctype="multipart/form-data">
                        <input name="file" type="file" accept=".pas" required>
                        <input type="submit" value="Отправить">
                    </form>
                </td>
            </tr>
            <tr align="center">
                <td>${result}</td>
            </tr>

                <c:if test="${expected != null}">
                    <tr>
                        <td>Входные данные</td>
                        <td>Ожидаемый результат</td>
                    </tr>
                    <tr>
                        <td>${input}</td>
                        <td>${expected}</td>
                    </tr>
                </c:if>

        </table>
    </div>
    <a href="/theory?theme_id=${theory.theme.id}#tab2">Результаты</a>

</c:if>


<script>
    function taskIsDone() {
        $('#is_done').removeAttr('disabled');
    }
</script>

</body>
</html>