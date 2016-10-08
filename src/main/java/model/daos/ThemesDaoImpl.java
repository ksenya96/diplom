package model.daos;

import org.hibernate.Session;


/**
 * Created by acer on 11.08.2016.
 */
public class ThemesDaoImpl extends DaoImp {
    public ThemesDaoImpl(Session session, Entity entity) {
        super(session, entity);
    }
}
