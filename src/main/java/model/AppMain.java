package model;

import controller.UserType;
import model.daos.*;
import model.entities.*;
import model.utils.HibernateSessionFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;

import java.io.*;
import java.util.List;


public class AppMain {

    public static void main(String[] args) throws IOException {

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

        *//*ThemesDaoImpl themesDao = new ThemesDaoImpl(SESSION, Entity.THEMES);
        TheoryDaoImpl theoryDao = new TheoryDaoImpl(SESSION, Entity.THEORY);
        Theory theory = theoryDao.getTheoryByTheme(6);


        List<Theme> themes = themesDao.getThemesByClass(7);
        Theme theme = (Theme) themesDao.getEntityById(6);
        for (Theme t: themes)
            System.out.println(t.getId() + " " + theory.getTheme().getId());
        themes.add(theory.getTheme());
        System.out.println(themes.contains(theory.getTheme()));
        System.out.println(themes.size());

        HibernateSessionFactory.shutdown();*/
        File file = new File("src\\main\\webapp\\tasks\\robot\\linear1.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Windows-1251"));
        System.out.print(reader.readLine());
    }

    public static void createUser(User user, String string) {
        System.out.println(user.getFirstName());
        System.out.println(string);
    }
}
