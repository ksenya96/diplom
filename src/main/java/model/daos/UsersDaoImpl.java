package model.daos;

import model.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


/**
 * Created by acer on 07.09.2016.
 */
public class UsersDaoImpl extends DaoImp {


    public UsersDaoImpl (Session session, Entity entity) {
        super(session, entity);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        Query query = getSession().createQuery("FROM "+ tableName + " AS t" +
                " WHERE t.login = '" + login + "'" + " AND t.password = '" + password + "'");
        return (User) query.uniqueResult();
    }

    public User getUserByLogin(String login) {
        Query query = getSession().createQuery("FROM "+ tableName + " AS t" +
                " WHERE t.login = '" + login + "'");
        return (User)query.uniqueResult();
    }
}
