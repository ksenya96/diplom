package controller;

import model.service.ModificationsForTasks;
import model.service.ModificationsForTheory;
import model.testingSystem.Checker;
import model.testingSystem.Comment;
import model.testingSystem.TestAndComment;
import model.entities.Task;
import model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by acer on 31.12.2016.
 */
@WebServlet(value = "/task", name = "ServletForTask")
@MultipartConfig(location = "d:\\")
public class ServletForTask extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        Task task = (Task)session.getAttribute("task");
        if (task.getType() == TaskType.PROGRAM) {
            Part filePart = request.getPart("file");
            TestAndComment testAndComment = ModificationsForTasks.checkTask(user, task, filePart);
            int test = testAndComment.getTest();
            String input = testAndComment.getInput();
            String expected = testAndComment.getOutput();
            Comment comment = testAndComment.getComment();
            switch (comment) {
                case OK:
                    session.setAttribute("result", "Программа прошла все тесты");
                    break;
                case RUNTIME_ERROR:
                    session.setAttribute("result", "Ошибка времени выполнения (логическая ошибка)");
                    break;
                case TIME_LIMIT:
                    session.setAttribute("result", "Превышен лимит времени (программа выполнялась слишком долго)");
                    break;
                case WRONG_ANSWER:
                    session.setAttribute("result", "Неверный ответ на тесте №" + test);
                    break;
                default:
                    session.setAttribute("result", "Ошибка компиляции (в файле содержится синтаксическая ошибка)");
            }
            session.setAttribute("input", input);
            session.setAttribute("expected", expected);


        }
        if (task.getType() == TaskType.ROBOT) {
            ModificationsForTasks.checkTask(user, task, null);
        }
        Servlet.redirectToUserJSP(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int taskId = Integer.parseInt(request.getParameter("task_id"));
        Task task = ModificationsForTasks.getTaskById(taskId);
        String dataInContent = ModificationsForTasks.getTaskContent(task);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("task_content", dataInContent);
            session.setAttribute("task", task);
            session.setAttribute("result", "");
            session.setAttribute("content", "task");
        }

        Servlet.redirectToUserJSP(request, response);

    }
}
