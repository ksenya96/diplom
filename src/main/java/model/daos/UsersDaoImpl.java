package model.daos;

import org.hibernate.Session;

/**
 * Created by acer on 07.09.2016.
 */
public class UsersDaoImpl extends DaoImpl {
    public UsersDaoImpl (Session session, Entity entity) {
        super(session, entity);
    }
}
