package model.daos;

import model.entities.TasksEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by acer on 23.08.2016.
 */
public class TasksDaoImpl extends DaoImpl {
    public TasksDaoImpl(Session session, Entity entity) {
        super(session, entity);
    }

    public List<TasksEntity> getTasksByTheme(int themeId) {
        String tableName = getEntity().getTableName();
        Query query = getSession().createQuery("FROM " + tableName + " AS t " +
                "WHERE t.themeId = " + themeId);
        return (List<TasksEntity>)query.list();
    }
}
