package controller;

import model.daos.*;
import model.entities.*;
import model.service.ModificationsForUser;
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
                redirectToIndexJSP(request, response);
                break;
        }
    }

    private void addChild(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int childId = Integer.parseInt(request.getParameter("child-hidden").trim());
        Parent parent = (Parent) request.getSession(false).getAttribute("user");
        ModificationsForUser.addChild(parent, childId);
        redirectToUserJSP(request, response);
    }

    private void addPupil(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int pupilId = Integer.parseInt(request.getParameter("child-hidden").trim());
        Teacher teacher = (Teacher) request.getSession(false).getAttribute("user");
        ModificationsForUser.addPupil(teacher, pupilId);
        redirectToUserJSP(request, response);
    }

    private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int teacherId = Integer.parseInt(request.getParameter("teacher-hidden").trim());
        Pupil pupil = (Pupil) request.getSession(false).getAttribute("user");
        ModificationsForUser.addTeacher(pupil, teacherId);
        redirectToUserJSP(request, response);
    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("lastName").trim();
        String firstName = request.getParameter("firstName").trim();
        String patronymic = request.getParameter("patronymic").trim();
        String clazz = request.getParameter("class");
        String schoolId = request.getParameter("school-hidden");
        User user = (User)request.getSession(false).getAttribute("user");
        ModificationsForUser.edit(user, lastName, firstName, patronymic, clazz, schoolId);
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
        User newUser = ModificationsForUser.register(login, password, access, firstName, lastName, patronymic);
        if (ModificationsForUser.userIsExist(login) == null) {
            createRole(newUser, access, request, response);
            login(request, response);
        }
        else {
            request.setAttribute("result", "Пользователь с таким логином уже существует или данные некорректны");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private void createRole(User user, UserType userType, HttpServletRequest request, HttpServletResponse response) {
        switch (userType) {
            case PUPIL:
                int clazz = Integer.parseInt(request.getParameter("clazz").trim());
                int schoolId = Integer.parseInt(request.getParameter("school-hidden").trim());
                int teacherId = Integer.parseInt(request.getParameter("teacher-hidden").trim());
                ModificationsForUser.createRole(user, userType, clazz, schoolId, teacherId);
                break;
            case TEACHER:
                ModificationsForUser.createRole(user, userType);
                break;
            case PARENT:
                int pupilId = Integer.parseInt(request.getParameter("child-hidden"));
                ModificationsForUser.createRole(user, userType, pupilId);
                break;
        }
    }


    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login").toLowerCase();
        String password = request.getParameter("password");
        User user = ModificationsForUser.login(login, password);

        if (user != null) {
            request.getSession(false).invalidate();
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("user", user);

            newSession.setAttribute("schools", ModificationsForUser.getEntities(Entity.SCHOOLS));
            newSession.setAttribute("teachers", ModificationsForUser.getEntities(Entity.TEACHERS));
            newSession.setAttribute("pupils", ModificationsForUser.getEntities(Entity.PUPILS));
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
                    ModificationsForUser.deleteTeacher(pupil, teacherId);
                    redirectToUserJSP(request, response);
                    break;
                case "delete_pupil":
                    int pupilId = Integer.parseInt(request.getParameter("pupil_id"));
                    Teacher teacher = (Teacher) request.getSession(false).getAttribute("user");
                    ModificationsForUser.deletePupil(teacher, pupilId);
                    redirectToUserJSP(request, response);
                    break;
                case "delete_child":
                    int childId = Integer.parseInt(request.getParameter("child_id"));
                    Parent parent = (Parent) request.getSession(false).getAttribute("user");
                    ModificationsForUser.deleteChild(parent, childId);
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
                session.removeAttribute("clazz");
                session.setAttribute("content", "main");
            }
            redirectToIndexJSP(request, response);
        }


    }

    protected static void redirectToIndexJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            session = request.getSession(true);
            session.setAttribute("schools", ModificationsForUser.getEntities(Entity.SCHOOLS));
            session.setAttribute("teachers", ModificationsForUser.getEntities(Entity.TEACHERS));
            session.setAttribute("pupils", ModificationsForUser.getEntities(Entity.PUPILS));
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