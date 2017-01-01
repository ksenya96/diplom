package controller;

import model.daos.Entity;
import model.daos.TasksDaoImpl;
import model.daos.TheoryDaoImpl;
import model.entities.Task;
import model.entities.Theme;
import model.entities.Theory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by acer on 12.12.2016.
 */
@WebServlet(name = "ServletForTheory")
public class ServletForTheory extends HttpServlet {
    private TheoryDaoImpl theoryDao = new TheoryDaoImpl(Servlet.SESSION, Entity.THEORY);
    private TasksDaoImpl tasksDao = new TasksDaoImpl(Servlet.SESSION, Entity.TASKS);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer themeId = Integer.parseInt(request.getParameter("theme_id"));
        Theory theory = theoryDao.getTheoryByTheme(themeId);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("theory", theory);
            session.setAttribute("content", "theory");
            if (session.getAttribute("user") != null) {
                List<Task> tasks = tasksDao.getTasksByTheme(themeId);
                session.setAttribute("tasks", tasks);
            }
        }
        Servlet.redirectToIndexJSP(request, response);
    }
}
