import model.daos.Entity;
import model.daos.ThemesDaoImpl;
import model.daos.UsersDaoImpl;
import model.entities.ThemesEntity;
import model.entities.UsersEntity;
import model.utils.HibernateSessionFactory;
import org.hibernate.Session;


public class AppMain {

    public static void main(String[] args) {

        Session SESSION  = HibernateSessionFactory.getSessionFactory().openSession();
        UsersDaoImpl usersDao = new UsersDaoImpl(SESSION, Entity.USERS);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin("kanaplya");
        usersEntity.setPassword("123");
        usersEntity.setAccess(1);
        usersEntity.setFirstName("Иван");
        usersEntity.setLastName("Канапляник");
        usersEntity.setPatronymic("Викторович");

        usersDao.create(usersEntity);

        /*ThemesEntity themesEntity = new ThemesEntity();
        themesEntity.setTitle("c++");
        themesEntity.setClazz(6);

        ThemesDaoImpl themesDao = new ThemesDaoImpl(SESSION, Entity.THEMES);
        themesDao.delete(themesEntity);*/


        HibernateSessionFactory.shutdown();
    }
}
