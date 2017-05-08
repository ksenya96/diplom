package model.daos;

import model.entities.Theory;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Created by acer on 20.08.2016.
 */
public class TheoryDaoImpl extends DaoImpl {

    public TheoryDaoImpl(Session session, Entity entity) {
        super(session, entity);
    }

    public Theory getTheoryByTheme(int themeId) {
        Query query = getSession().createQuery(
                                          "FROM " + tableName + " AS t " +
                                          "WHERE t.theme.id = " + themeId);
        return (Theory) query.uniqueResult();
    }
}
