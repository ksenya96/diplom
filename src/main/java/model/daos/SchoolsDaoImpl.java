package model.daos;

import org.hibernate.Session;

/**
 * Created by acer on 07.09.2016.
 */
public class SchoolsDaoImpl extends DaoImp {
    public SchoolsDaoImpl (Session session, Entity entity) {
        super(session, entity);
    }
}
