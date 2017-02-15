package model;

import controller.UserType;
import model.daos.*;
import model.entities.*;
import model.utils.HibernateSessionFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;

import java.io.File;
import java.util.List;


public class AppMain {

    public static void main(String[] args) {

        //Session SESSION  = HibernateSessionFactory.getSessionFactory().openSession();
        /*UsersDaoImpl usersDao = new UsersDaoImpl(SESSION, Entity.USERS);
        TeachersDaoImpl teachersDao = new TeachersDaoImpl(SESSION, Entity.TEACHERS);


        PupilsDaoImpl pupilsDao = new PupilsDaoImpl(SESSION, Entity.PUPILS);
        User usersEntity = new User();
        usersEntity.setLogin("kanaplya");
        usersEntity.setPassword("123");
        usersEntity.setAccess(1);
        usersEntity.setFirstName("Иван");
        usersEntity.setLastName("Канапляник");
        usersEntity.setPatronymic("Викторович");


        usersDao.create(usersEntity);

        Pupil pupilsEntity = new Pupil();
        pupilsEntity.setClazz(6);
        pupilsEntity.setSchoolId(1);
        pupilsEntity.setUser(usersEntity);
        usersEntity.setPupil(pupilsEntity);

        pupilsDao.create(pupilsEntity);*/



        /*Theme themesEntity = new Theme();
        themesEntity.setTitle("c++");
        themesEntity.setClazz(6);

        ThemesDaoImpl themesDao = new ThemesDaoImpl(SESSION, Entity.THEMES);
        themesDao.delete(themesEntity);*/

        File file = new File("D:\\Моя папка\\Мой универ\\Test\\src\\main\\webapp\\tasks\\program\\tests\\RepublicDay");
        System.out.println(file.listFiles());

        HibernateSessionFactory.shutdown();
    }

    public static void createUser(User user, String string) {
        System.out.println(user.getFirstName());
        System.out.println(string);
    }
}
