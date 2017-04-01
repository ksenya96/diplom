package controller;

import model.daos.*;
import model.entities.*;
import model.service.ModificationsForTheory;
import model.service.ModificationsForUser;

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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        if (action == null) {
            int themeId = Integer.parseInt(request.getParameter("theme_id"));
            Theory theory = ModificationsForTheory.getTheoryByTheme(themeId);

            if (session != null) {
                session.setAttribute("theory", theory);
                session.setAttribute("content", "theory");
                User user = (User)session.getAttribute("user");
                if (user != null) {
                    if (user.getAccess() == UserType.PUPIL && ((Pupil)user).getThemes().contains(theory.getTheme())
                            || user.getAccess() != UserType.PUPIL) {
                        List<Task> tasks = ModificationsForTheory.getTasksByTheme(themeId);
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
                    ModificationsForTheory.subscribeToTheme(pupil, theory);
                    List<Task> tasks = ModificationsForTheory.getTasksByTheme(theory.getTheme().getId());
                    session.setAttribute("tasks", tasks);
                    Servlet.redirectToIndexJSP(request, response);
                    break;
            }
        }
    }
}
