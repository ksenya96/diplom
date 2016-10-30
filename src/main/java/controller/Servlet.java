package controller;

import model.AppMain;
import model.daos.*;
import model.entities.*;
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
    private PupilsDaoImpl pupilsDao = new PupilsDaoImpl(SESSION, Entity.PUPILS);
    private ParentsDaoImpl parentsDao = new ParentsDaoImpl(SESSION, Entity.PARENTS);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
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
        String lastName = request.getParameter("lastName").trim();
        String firstName = request.getParameter("firstName").trim();
        String patronymic = request.getParameter("patronymic").trim();
        String login = request.getParameter("login").trim();
        String password = DigestUtils.md5Hex(request.getParameter("password")).trim();
        String repeatPassword = DigestUtils.md5Hex(request.getParameter("confirmPassword"));
        String accessStr = request.getParameter("access");
        UserType access = UserType.valueOf(accessStr);
        User newUser = new User(login, password, access, firstName, lastName);
        newUser.setPatronymic(patronymic);
        if (usersDao.getUserByLogin(login) == null) {
            createRole(newUser, access, request, response);
        }
        else {
            PrintWriter printWriter = response.getWriter();
            printWriter.println("Пользователь с таким логином уже существует");
        }
    }

    private AbstractEntity createRole(User user, UserType userType, HttpServletRequest request, HttpServletResponse response) {
        switch (userType) {
            case PUPIL:
                int clazz = Integer.parseInt(request.getParameter("clazz"));
                int schoolId = Integer.parseInt(request.getParameter("school-hidden"));
                int teacherId = Integer.parseInt(request.getParameter("teacher-hidden"));
                Pupil pupil = new Pupil(user.getLogin(), user.getPassword(), user.getAccess(), user.getFirstName(), user.getLastName());
                pupil.setPatronymic(user.getPatronymic());
                pupil.setClazz(clazz);
                pupil.setSchool((School) schoolsDao.getEntityById(schoolId));
                pupil.getTeachers().add((Teacher)teachersDao.getEntityById(teacherId));
                try (PrintWriter writer = response.getWriter()) {
                    pupilsDao.create(pupil);
                    writer.println("Success");
                } catch (Exception e) {

                }
                break;
            case TEACHER:
                Teacher teacher = new Teacher(user.getLogin(), user.getPassword(), user.getAccess(), user.getFirstName(), user.getLastName());
                teacher.setPatronymic(request.getParameter("patronymic"));
                try {
                    teachersDao.create(teacher);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PARENT:
                int pupilId = Integer.parseInt(request.getParameter("child-hidden"));
                Parent parent = new Parent(user.getLogin(), user.getPassword(), user.getAccess(), user.getFirstName(), user.getLastName());
                parent.setPatronymic(request.getParameter("patronymic"));
                parent.getPupils().add((Pupil)pupilsDao.getEntityById(pupilId));
                try {
                    parentsDao.create(parent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return null;
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
                //request.setAttribute("themes", themesDao.getAllEntities());
                request.setAttribute("schools", schoolsDao.getAllEntities());
                request.setAttribute("teachers", teachersDao.getAllEntities());
                request.setAttribute("pupils", pupilsDao.getAllEntities());
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
            //String title = themesDao.getEntityById(id);
            //request.setAttribute("title", title);
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
