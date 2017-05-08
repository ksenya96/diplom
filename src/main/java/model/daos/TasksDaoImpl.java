package model.daos;

import model.entities.Task;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by acer on 23.08.2016.
 */
public class TasksDaoImpl extends DaoImpl {
    public TasksDaoImpl(Session session, Entity entity) {
        super(session, entity);
    }

    public List<Task> getTasksByTheme(int themeId) {
        Query query = getSession().createQuery("FROM " + tableName + " AS t " +
                "WHERE t.theme.id = " + themeId);
        return (List<Task>)query.list();
    }
}
