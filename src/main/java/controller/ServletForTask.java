package controller;

import model.daos.Entity;
import model.daos.TasksDaoImpl;
import model.entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by acer on 31.12.2016.
 */
@WebServlet(name = "ServletForTask")
public class ServletForTask extends HttpServlet {
    private TasksDaoImpl tasksDao = new TasksDaoImpl(Servlet.SESSION, Entity.TASKS);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("task_id"));
        Task task = (Task) tasksDao.getEntityById(taskId);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("task", task);
            session.setAttribute("content", "task");
        }
        Servlet.redirectToUserJSP(request, response);
    }
}
