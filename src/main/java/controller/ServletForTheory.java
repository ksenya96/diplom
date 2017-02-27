package controller;

import model.daos.*;
import model.entities.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by acer on 12.12.2016.
 */
@WebServlet(name = "ServletForTheory")
public class ServletForTheory extends HttpServlet {
    private TheoryDaoImpl theoryDao = new TheoryDaoImpl(Servlet.SESSION, Entity.THEORY);
    private TasksDaoImpl tasksDao = new TasksDaoImpl(Servlet.SESSION, Entity.TASKS);
    //private PupilsDaoImpl pupilsDao = new PupilsDaoImpl(Servlet.SESSION, Entity.PUPILS);

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
                User user = (User)session.getAttribute("user");
                if (user != null) {
                    if (user.getAccess() == UserType.PUPIL && ((Pupil)user).getThemes().contains(theory.getTheme())
                            || user.getAccess() != UserType.PUPIL) {
                        List<Task> tasks = tasksDao.getTasksByTheme(themeId);
                        session.setAttribute("tasks", tasks);
                    }
                }
            }
            Servlet.redirectToIndexJSP(request, response);
        }
        else {
            switch (action) {
                case "subscribe":
                    Pupil pupil = (Pupil)session.getAttribute("user");
                    Theory theory = (Theory)session.getAttribute("theory");
                    Theme theme = theory.getTheme();
                    pupil.getThemes().add(theme);
                    PupilsDaoImpl pupilsDao = new PupilsDaoImpl(Servlet.SESSION, Entity.PUPILS);
                    pupilsDao.update(pupil);
                    List<Task> tasks = tasksDao.getTasksByTheme(theory.getTheme().getId());
                    session.setAttribute("tasks", tasks);
                    if (!theme.getPupils().contains(pupil))
                        theme.getPupils().add(pupil);
                    Servlet.redirectToIndexJSP(request, response);
                    break;
            }
        }
    }
}
