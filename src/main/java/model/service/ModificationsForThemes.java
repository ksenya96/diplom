package model.service;

import controller.Servlet;
import model.daos.Entity;
import model.daos.ThemesDaoImpl;
import model.entities.Theme;

import java.util.List;

/**
 * Created by acer on 01.04.2017.
 */
public class ModificationsForThemes {
    private static ThemesDaoImpl themesDao = new ThemesDaoImpl(Servlet.SESSION, Entity.THEMES);

    public static List<Theme> getThemesByClass(int clazz) {
        return themesDao.getThemesByClass(clazz);
    }
}
