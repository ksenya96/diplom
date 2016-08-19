import model.daos.Dao;
import model.daos.DaoFactory;
import model.daos.Entity;
import model.entities.AbstractEntity;
import model.entities.ThemesEntity;
import model.utils.HibernateSessionFactory;

import java.util.List;


public class AppMain {

    public static void main(String[] args) {

        Dao themesDao = DaoFactory.getDao(Entity.THEMES);
        /*ThemesEntity themesEntity = new ThemesEntity();
        themesEntity.setId(6);
        themesEntity.setTitle("pascal");
        themesEntity.setClazz(6);
        themesDao.update(themesEntity);*/
        List<? extends AbstractEntity> list = themesDao.getAllEntities();

        //System.out.println(list);
        for (AbstractEntity entity: list)
            System.out.println(entity);

        HibernateSessionFactory.shutdown();
    }
}
