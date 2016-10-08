package model.daos;

import org.hibernate.Session;

/**
 * Created by acer on 07.10.2016.
 */
public class DaoFactory {
    public static DaoImp getDao(Session session, Entity entity) {
        switch (entity) {
            case THEMES:
                return new ThemesDaoImpl(session, entity);
            case THEORY:
                return new TheoryDaoImpl(session, entity);
            case TASKS:
                return new TasksDaoImpl(session, entity);
            case USERS:
                return new UsersDaoImpl(session, entity);
            case SCHOOLS:
                return new SchoolsDaoImpl(session, entity);
            case TEACHERS:
                return new TeachersDaoImpl(session, entity);
        }
        return null;
    }
}
