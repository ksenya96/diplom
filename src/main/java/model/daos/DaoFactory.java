package model.daos;

import model.utils.HibernateSessionFactory;
import org.hibernate.Session;

/**
 * Created by acer on 11.08.2016.
 */
public class DaoFactory {
    public  static final Session SESSION = HibernateSessionFactory.getSessionFactory().openSession();
    public static Dao getDao(Entity entity) {
        switch (entity) {
            case THEMES:
                return new ThemesDao(SESSION, entity);
            default:
                return null;
        }
    }
}
