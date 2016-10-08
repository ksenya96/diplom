package model.daos;

import org.hibernate.Session;

/**
 * Created by acer on 07.09.2016.
 */
public class PupilsDaoImpl extends DaoImp {
    public PupilsDaoImpl(Session session, Entity entity) {
        super(session, entity);
    }
}
