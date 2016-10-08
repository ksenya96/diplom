package model.daos;

import model.entities.Teacher;
import model.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Created by acer on 07.09.2016.
 */
public class UsersDaoImpl extends DaoImp {
    public UsersDaoImpl (Session session, Entity entity) {
        super(session, entity);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        String tableName = getEntity().getTableName();
        Query query = getSession().createQuery("FROM "+ tableName + " AS t" +
                " WHERE t.login = '" + login + "'" + " AND t.password = '" + password + "'");
        return (User) query.uniqueResult();
    }

    public Teacher findTeacherById(int id) {
        Query query = getSession().createQuery("from Teacher t where t.id = :id");
        query.setParameter("id", id);
        return (Teacher) query.uniqueResult();
    }
}
