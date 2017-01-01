package model.daos;

import model.entities.Theme;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


/**
 * Created by acer on 11.08.2016.
 */
public class ThemesDaoImpl extends DaoImp {
    public ThemesDaoImpl(Session session, Entity entity) {
        super(session, entity);
    }

    public List<Theme> getThemesByClass(int clazz) {
        Query query = getSession().createQuery("FROM " + tableName + " AS t" + " WHERE t.clazz=" + clazz);
        return (List<Theme>)query.list();
    }
}
