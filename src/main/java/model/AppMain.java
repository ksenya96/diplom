package model;

import controller.UserType;
import model.daos.*;
import model.entities.Pupil;
import model.entities.School;
import model.entities.Teacher;
import model.entities.User;
import model.utils.HibernateSessionFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;

import java.util.List;


public class AppMain {

    public static void main(String[] args) {

        Session SESSION  = HibernateSessionFactory.getSessionFactory().openSession();
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

        UsersDaoImpl usersDao = new UsersDaoImpl(SESSION, Entity.USERS);
        Pupil pupil = new Pupil("vlad", DigestUtils.md5Hex("123"), UserType.PUPIL, "Владик", "Сыщенко");
        pupil.setClazz(2);
        SchoolsDaoImpl schoolsDao = new SchoolsDaoImpl(SESSION, Entity.SCHOOLS);
        School school = (School)schoolsDao.getEntityById(1);
        pupil.setSchool(school);
        school.getPupils().add(pupil);
        usersDao.create(pupil);
        school = (School)schoolsDao.getEntityById(1);
        for (Pupil p: school.getPupils())
            System.out.println(p.getLastName());
        HibernateSessionFactory.shutdown();
    }

    public static void createUser(User user, String string) {
        System.out.println(user.getFirstName());
        System.out.println(string);
    }
}
