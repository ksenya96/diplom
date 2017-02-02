package controller;

import model.daos.Entity;
import model.daos.PupilsDaoImpl;
import model.daos.TasksDaoImpl;
import model.daos.TheoryDaoImpl;
import model.entities.*;

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
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        if (action == null) {
            Integer themeId = Integer.parseInt(request.getParameter("theme_id"));
            Theory theory = theoryDao.getTheoryByTheme(themeId);
            if (session != null) {
                session.setAttribute("theory", theory);
                session.setAttribute("content", "theory");
                if (session.getAttribute("user") != null) {
                    List<Task> tasks = tasksDao.getTasksByTheme(themeId);
                    session.setAttribute("tasks", tasks);
                }
            }
        }
        else {
            switch (action) {
                case "subscribe":
                    Pupil pupil = (Pupil)session.getAttribute("user");
                    Theory theory = (Theory)session.getAttribute("theory");
                    pupil.getThemes().add(theory.getTheme());
                    PupilsDaoImpl pupilsDao = new PupilsDaoImpl(Servlet.SESSION, Entity.PUPILS);
                    pupilsDao.update(pupil);
                    break;
            }
        }
        Servlet.redirectToIndexJSP(request, response);
    }
}
