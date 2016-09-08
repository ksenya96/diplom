package model.daos;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


/**
 * Created by acer on 20.08.2016.
 */
public class TheoryDaoImpl extends DaoImpl {

    public TheoryDaoImpl(Session session, Entity entity) {
        super(session, entity);
    }

    public String getContentByTheme(int themeId) {
        String tableName = getEntity().getTableName();
        Query query = getSession().createQuery("SELECT t.content " +
                                          "FROM " + tableName + " AS t " +
                                          "WHERE t.themeId = " + themeId);
        return ((List<String>)query.list()).get(0);
    }
}
