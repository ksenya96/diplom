package model.daos;

import org.hibernate.Session;

/**
 * Created by acer on 07.09.2016.
 */
public class TeachersDaoImpl extends DaoImpl {
    public TeachersDaoImpl(Session session, Entity entity) {
        super(session, entity);
    }
}
