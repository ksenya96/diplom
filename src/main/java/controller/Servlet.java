package controller;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Created by acer on 11.08.2016.
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    public static final Session SESSION  = HibernateSessionFactory.getSessionFactory().openSession();
    private UsersDaoImpl usersDao = new UsersDaoImpl(SESSION, Entity.USERS);
    private static SchoolsDaoImpl schoolsDao = new SchoolsDaoImpl(SESSION, Entity.SCHOOLS);
    private static TeachersDaoImpl teachersDao = new TeachersDaoImpl(SESSION, Entity.TEACHERS);
    private static PupilsDaoImpl pupilsDao = new PupilsDaoImpl(SESSION, Entity.PUPILS);
    private ParentsDaoImpl parentsDao = new ParentsDaoImpl(SESSION, Entity.PARENTS);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.indexOf('#') > -1)
            action = action.substring(0, action.indexOf('#'));
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "add_teacher":
                addTeacher(request, response);
                break;
            case "add_pupil":
                addPupil(request, response);
                break;
            case "add_child":
                addChild(request, response);
                break;
            case "exit":
                request.getSession(false).invalidate();
                //request.setAttribute("user", null);
                redirectToIndexJSP(request, response);
                break;
        }
    }

    private void addChild(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int childId = Integer.parseInt(request.getParameter("child-hidden").trim());
        Parent parent = (Parent) request.getSession(false).getAttribute("user");
        parent.getPupils().add((Pupil) pupilsDao.getEntityById(childId));
        parentsDao.update(parent);
        //request.setAttribute("pupils", pupilsDao.getAllEntities());
        redirectToUserJSP(request, response);
    }

    private void addPupil(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int pupilId = Integer.parseInt(request.getParameter("child-hidden").trim());
        Teacher teacher = (Teacher) request.getSession(false).getAttribute("user");
        teacher.getPupils().add((Pupil) pupilsDao.getEntityById(pupilId));
        teachersDao.update(teacher);
        //request.setAttribute("pupils", pupilsDao.getAllEntities());
        redirectToUserJSP(request, response);
    }

    private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int teacherId = Integer.parseInt(request.getParameter("teacher-hidden").trim());
        Pupil pupil = (Pupil) request.getSession(false).getAttribute("user");
        pupil.getTeachers().add((Teacher)teachersDao.getEntityById(teacherId));
        pupilsDao.update(pupil);
        //request.setAttribute("teachers", teachersDao.getAllEntities());
        redirectToUserJSP(request, response);
    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("lastName").trim();
        String firstName = request.getParameter("firstName").trim();
        String patronymic = request.getParameter("patronymic").trim();
        User user = (User)request.getSession(false).getAttribute("user");
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPatronymic(patronymic);
        switch (user.getAccess()) {
            case PUPIL:
                Pupil pupil = (Pupil)user;
                int clazz = Integer.parseInt(request.getParameter("class").trim());
                pupil.setClazz(clazz);
                int schoolId = Integer.parseInt(request.getParameter("school-hidden").trim());
                pupil.setSchool((School) schoolsDao.getEntityById(schoolId));
                pupilsDao.update(pupil);
                break;
            case TEACHER:
                teachersDao.update(user);
                break;
            case PARENT:
                parentsDao.update(user);
                break;
        }
        redirectToUserJSP(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("lastName").trim();
        String firstName = request.getParameter("firstName").trim();
        String patronymic = request.getParameter("patronymic").trim();
        String login = request.getParameter("login").trim().toLowerCase();
        String password = DigestUtils.md5Hex(request.getParameter("password")).trim();
        String accessStr = request.getParameter("access");
        UserType access = UserType.valueOf(accessStr);
        User newUser = new User(login, password, access, firstName, lastName);
        newUser.setPatronymic(patronymic);
        if (usersDao.getUserByLogin(login) == null) {
            createRole(newUser, access, request, response);
            login(request, response);
        }
        else {
            request.setAttribute("result", "Пользователь с таким логином уже существует или данные некорректны");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private AbstractEntity createRole(User user, UserType userType, HttpServletRequest request, HttpServletResponse response) {
        switch (userType) {
            case PUPIL:
                int clazz = Integer.parseInt(request.getParameter("clazz").trim());
                int schoolId = Integer.parseInt(request.getParameter("school-hidden").trim());
                int teacherId = Integer.parseInt(request.getParameter("teacher-hidden").trim());
                Pupil pupil = new Pupil(user.getLogin(), user.getPassword(), user.getAccess(), user.getFirstName(), user.getLastName());
                pupil.setPatronymic(user.getPatronymic());
                pupil.setClazz(clazz);
                pupil.setSchool((School) schoolsDao.getEntityById(schoolId));
                pupil.getTeachers().add((Teacher)teachersDao.getEntityById(teacherId));
                try {
                    pupilsDao.create(pupil);
                } catch (Exception e) {
                    e.printStackTrace();
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
        String login = request.getParameter("login").toLowerCase();
        String password = request.getParameter("password");
        password = DigestUtils.md5Hex(password);
        User user = usersDao.getUserByLoginAndPassword(login, password);

        if (user != null) {
            request.getSession(false).invalidate();
            HttpSession newSession = request.getSession(true);
            switch (user.getAccess()) {
                case PUPIL:
                    Pupil pupil = (Pupil)pupilsDao.getEntityById(user.getId());
                    newSession.setAttribute("user", pupil);
                    break;
                case TEACHER:
                    Teacher teacher = (Teacher) teachersDao.getEntityById(user.getId());
                    newSession.setAttribute("user", teacher);
                    break;
                case PARENT:
                    Parent parent = (Parent) parentsDao.getEntityById(user.getId());
                    newSession.setAttribute("user", parent);
                    break;
            }
            newSession.setAttribute("schools", schoolsDao.getAllEntities());
            newSession.setAttribute("teachers", teachersDao.getAllEntities());
            newSession.setAttribute("pupils", pupilsDao.getAllEntities());
            newSession.setAttribute("content", "main");
            redirectToUserJSP(request, response);
        }
        else {
            request.setAttribute("result", "Данные некорректны");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            if (action.indexOf('#') > -1)
                action = action.substring(0, action.indexOf('#'));
            switch (action) {
                case "delete_teacher":
                    int teacherId = Integer.parseInt(request.getParameter("teacher_id"));
                    Pupil pupil = (Pupil) request.getSession(false).getAttribute("user");
                    pupil.getTeachers().remove(teachersDao.getEntityById(teacherId));
                    pupilsDao.update(pupil);
                    redirectToUserJSP(request, response);
                    break;
                case "delete_pupil":
                    int pupilId = Integer.parseInt(request.getParameter("pupil_id"));

                    Teacher teacher = (Teacher) request.getSession(false).getAttribute("user");
                    teacher.getPupils().remove(pupilsDao.getEntityById(pupilId));
                    teachersDao.update(teacher);
                    redirectToUserJSP(request, response);
                    break;
                case "delete_child":
                    int childId = Integer.parseInt(request.getParameter("child_id"));
                    Parent parent = (Parent) request.getSession(false).getAttribute("user");
                    parent.getPupils().remove(pupilsDao.getEntityById(childId));
                    parentsDao.update(parent);
                    redirectToUserJSP(request, response);
                    break;
                default:
                    HttpSession session = request.getSession(false);
                    if (session != null)
                        session.setAttribute("content", "main");
                    redirectToIndexJSP(request, response);
            }
        }

        else {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("content", "main");
            }
            redirectToIndexJSP(request, response);
        }


    }

    protected static void redirectToIndexJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            session = request.getSession(true);
            session.setAttribute("schools", schoolsDao.getAllEntities());
            session.setAttribute("teachers", teachersDao.getAllEntities());
            session.setAttribute("pupils", pupilsDao.getAllEntities());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            //session.setAttribute("content", "main");
            if (!session.getAttribute("content").equals("task")) {
                session.removeAttribute("result");
                session.removeAttribute("input");
                session.removeAttribute("expected");
            }
            redirectToUserJSP(request, response);
        }
    }

    protected static void redirectToUserJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        SESSION.close();
        HibernateSessionFactory.shutdown();
        super.destroy();
    }

}