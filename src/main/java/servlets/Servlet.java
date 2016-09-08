package servlets;

import model.daos.*;
import model.utils.HibernateSessionFactory;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by acer on 11.08.2016.
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    private static final Session SESSION  = HibernateSessionFactory.getSessionFactory().openSession();
    private ThemesDaoImpl themesDao = new ThemesDaoImpl(SESSION, Entity.THEMES);
    private TheoryDaoImpl theoryDao = new TheoryDaoImpl(SESSION, Entity.THEORY);
    private TasksDaoImpl tasksDao = new TasksDaoImpl(SESSION, Entity.TASKS);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theme = request.getParameter("theme");
        String task = request.getParameter("task");

        if (theme == null) {
            //начальная страница
            if (task == null) {
                request.setAttribute("themes", themesDao.getAllEntities());
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            //задание
            else {
                int taskId = Integer.parseInt(task);
                request.setAttribute("tasks", taskId);
                request.getRequestDispatcher("/task.jsp").forward(request, response);
            }
        }
        //страница с теорией
        else {
            int id = Integer.parseInt(theme);
            String title = themesDao.getEntityById(id);
            request.setAttribute("title", title);
            String content = theoryDao.getContentByTheme(id);
            request.setAttribute("content", content);
            request.setAttribute("tasks", tasksDao.getTasksByTheme(id));
            request.getRequestDispatcher("/theory.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        SESSION.close();
        HibernateSessionFactory.shutdown();
        super.destroy();
    }
}
