package model.service;

import controller.Servlet;
import controller.UserType;
import model.daos.*;
import model.entities.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * Created by acer on 01.04.2017.
 */
public class ModificationsForUser {
    private static ParentsDaoImpl parentsDao = new ParentsDaoImpl(Servlet.SESSION, Entity.PARENTS);
    private static PupilsDaoImpl pupilsDao = new PupilsDaoImpl(Servlet.SESSION, Entity.PUPILS);
    private static TeachersDaoImpl teachersDao = new TeachersDaoImpl(Servlet.SESSION, Entity.TEACHERS);
    private static SchoolsDaoImpl schoolsDao = new SchoolsDaoImpl(Servlet.SESSION, Entity.SCHOOLS);
    private static UsersDaoImpl usersDao = new UsersDaoImpl(Servlet.SESSION, Entity.USERS);

    public static void addChild(Parent parent, int childId) {
        parent.getPupils().add((Pupil) pupilsDao.getEntityById(childId));
        parentsDao.update(parent);
    }

    public static void addPupil(Teacher teacher, int pupilId) {
        teacher.getPupils().add((Pupil) pupilsDao.getEntityById(pupilId));
        teachersDao.update(teacher);
    }

    public static void addTeacher(Pupil pupil, int teacherId) {
        pupil.getTeachers().add((Teacher)teachersDao.getEntityById(teacherId));
        pupilsDao.update(pupil);
    }

    public static void edit(User user, String lastName, String firstName, String patronymic, String clazz, String schoolId) {
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPatronymic(patronymic);
        switch (user.getAccess()) {
            case PUPIL:
                Pupil pupil = (Pupil)user;
                pupil.setClazz(Integer.parseInt(clazz.trim()));
                pupil.setSchool((School) schoolsDao.getEntityById(Integer.parseInt(schoolId.trim())));
                pupilsDao.update(pupil);
                break;
            case TEACHER:
                teachersDao.update(user);
                break;
            case PARENT:
                parentsDao.update(user);
                break;
        }
    }

    public static User login(String login, String password) {
        password = DigestUtils.md5Hex(password);
        User user = usersDao.getUserByLoginAndPassword(login, password);
        if (user == null) return null;
        switch (user.getAccess()) {
            case PUPIL: return (Pupil)pupilsDao.getEntityById(user.getId());
            case TEACHER: return (Teacher) teachersDao.getEntityById(user.getId());
            case PARENT: return (Parent) parentsDao.getEntityById(user.getId());
        }
        return null;
    }

    public static User register (String login, String password, UserType access, String firstName, String lastName, String patronymic) {
        User newUser = new User(login, password, access, firstName, lastName);
        newUser.setPatronymic(patronymic);
        return newUser;
    }

    public static User userIsExist(String login) {
        return usersDao.getUserByLogin(login);
    }

    public static void createRole(User user, UserType userType, int... params) {
        switch (userType) {
            case PUPIL:
                Pupil pupil = new Pupil(user.getLogin(), user.getPassword(), user.getAccess(), user.getFirstName(), user.getLastName());
                pupil.setPatronymic(user.getPatronymic());
                pupil.setClazz(params[0]);
                pupil.setSchool((School) schoolsDao.getEntityById(params[1]));
                pupil.getTeachers().add((Teacher)teachersDao.getEntityById(params[2]));
                try {
                    pupilsDao.create(pupil);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case TEACHER:
                Teacher teacher = new Teacher(user.getLogin(), user.getPassword(), user.getAccess(), user.getFirstName(), user.getLastName());
                teacher.setPatronymic(user.getPatronymic());
                try {
                    teachersDao.create(teacher);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case PARENT:
                Parent parent = new Parent(user.getLogin(), user.getPassword(), user.getAccess(), user.getFirstName(), user.getLastName());
                parent.setPatronymic(user.getPatronymic());
                parent.getPupils().add((Pupil)pupilsDao.getEntityById(params[0]));
                try {
                    parentsDao.create(parent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public static void deleteTeacher(Pupil pupil, int teacherId) {
        pupil.getTeachers().remove(teachersDao.getEntityById(teacherId));
        pupilsDao.update(pupil);
    }

    public static void deletePupil(Teacher teacher, int pupilId) {
        teacher.getPupils().remove(pupilsDao.getEntityById(pupilId));
        teachersDao.update(teacher);
    }

    public static void deleteChild(Parent parent, int childId) {
        parent.getPupils().remove(pupilsDao.getEntityById(childId));
        parentsDao.update(parent);
    }

    public static List getEntities(Entity entity) {
        switch (entity) {
            case SCHOOLS:
                return schoolsDao.getAllEntities();
            case PUPILS:
                return pupilsDao.getAllEntities();
            case TEACHERS:
                return teachersDao.getAllEntities();
        }
        return null;

    }
}
