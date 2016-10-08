package controller;

import model.daos.*;
import model.entities.School;
import model.entities.Teacher;
import model.entities.User;
import model.utils.HibernateSessionFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Created by acer on 11.08.2016.
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    private static final Session SESSION  = HibernateSessionFactory.getSessionFactory().openSession();
    private ThemesDaoImpl themesDao = new ThemesDaoImpl(SESSION, Entity.THEMES);
    private TheoryDaoImpl theoryDao = new TheoryDaoImpl(SESSION, Entity.THEORY);
    private TasksDaoImpl tasksDao = new TasksDaoImpl(SESSION, Entity.TASKS);
    private UsersDaoImpl usersDao = new UsersDaoImpl(SESSION, Entity.USERS);
    private SchoolsDaoImpl schoolsDao = new SchoolsDaoImpl(SESSION, Entity.SCHOOLS);
    private TeachersDaoImpl teachersDao = new TeachersDaoImpl(SESSION, Entity.TEACHERS);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        switch (type) {
            case "login":
                login(request, response);
                break;
            case "register":
                register(request, response);
                break;
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String patronymic = request.getParameter("patronymic");
        String login = request.getParameter("login");
        String password = DigestUtils.md5Hex(request.getParameter("password"));
        String repeatPassword = DigestUtils.md5Hex(request.getParameter("confirmPassword"));
        PrintWriter writer = response.getWriter();
        String accessStr = request.getParameter("access");
        UserType userType = UserType.valueOf(accessStr);
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPatronymic(patronymic);
        user.setLogin(login);
        user.setPassword(password);

        usersDao.create(user);
        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teachersDao.create(teacher);
        writer.write("Пользователь создан");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password = DigestUtils.md5Hex(password);
        User user = usersDao.getUserByLoginAndPassword(login, password);

        if (user != null) {
            request.setAttribute("result", user.getFirstName());
        }
        else
            request.setAttribute("result", "Данные некорректны");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String theme = request.getParameter("theme");
        String task = request.getParameter("task");

        if (theme == null) {
            //начальная страница
            if (task == null) {
                request.setAttribute("themes", themesDao.getAllEntities());
                request.setAttribute("schools", schoolsDao.getAllEntities());
                request.setAttribute("teachers", teachersDao.getAllEntities());
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
