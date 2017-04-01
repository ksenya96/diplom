package model.service;

import controller.Servlet;
import model.daos.*;
import model.entities.Pupil;
import model.entities.Task;
import model.entities.Theme;
import model.entities.Theory;

import java.util.List;

/**
 * Created by acer on 01.04.2017.
 */
public class ModificationsForTheory {
    private static ThemesDaoImpl themesDao = new ThemesDaoImpl(Servlet.SESSION, Entity.THEMES);
    private static TheoryDaoImpl theoryDao = new TheoryDaoImpl(Servlet.SESSION, Entity.THEORY);
    private static TasksDaoImpl tasksDao = new TasksDaoImpl(Servlet.SESSION, Entity.TASKS);

    public static Theory getTheoryByTheme(int themeId) {
        return theoryDao.getTheoryByTheme(themeId);
    }

    public static List<Task> getTasksByTheme(int themeId) {
        return tasksDao.getTasksByTheme(themeId);
    }

    public static void subscribeToTheme(Pupil pupil, Theory theory) {
        Theme theme = theory.getTheme();
        pupil.getThemes().add(theme);
        PupilsDaoImpl pupilsDao = new PupilsDaoImpl(Servlet.SESSION, Entity.PUPILS);
        pupilsDao.update(pupil);
        if (!theme.getPupils().contains(pupil))
            theme.getPupils().add(pupil);
    }
}
