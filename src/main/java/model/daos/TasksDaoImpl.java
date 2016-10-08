package model.daos;

import model.entities.Task;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by acer on 23.08.2016.
 */
public class TasksDaoImpl extends DaoImp {
    public TasksDaoImpl(Session session, Entity entity) {
        super(session, entity);
    }

    public List<Task> getTasksByTheme(int themeId) {
        String tableName = getEntity().getTableName();
        Query query = getSession().createQuery("FROM " + tableName + " AS t " +
                "WHERE t.themeId = " + themeId);
        return (List<Task>)query.list();
    }
}
