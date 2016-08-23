package model.daos;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


/**
 * Created by acer on 11.08.2016.
 */
public class ThemesDao extends Dao{


    public ThemesDao(Session session, Entity entity) {
        super(session, entity);
    }

    public String getTitleById(int id) {
        String tableName = getEntity().getTableName();
        Query query = getSession().createQuery
                ("SELECT t.title " +
                        "FROM " + tableName + " AS t " +
                        "WHERE t.id = " + id);
        return ((List<String>)query.list()).get(0);
    }

}
