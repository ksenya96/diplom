package model.daos;

import model.entities.AbstractEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by acer on 14.09.2016.
 */
public class DaoImpl {
    private Session session;
    private Entity entity;
    protected String tableName;

    public DaoImpl(Session session, Entity entity) {
        this.session = session;
        this.entity = entity;
        tableName = getEntity().getTableName();
    }


    public void create(AbstractEntity entity) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void read() {

    }

    public void update(AbstractEntity entity) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void delete(AbstractEntity entity) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public List<? extends AbstractEntity> getAllEntities() {
        session.createQuery("FROM " + entity.getTableName());
        return session.createQuery("FROM " + entity.getTableName()).list();
    }

    public AbstractEntity getEntityById(int id) {
        String tableName = getEntity().getTableName();

        Query query = getSession().createQuery
                ("FROM " + tableName + " AS t " +
                        "WHERE t.id = " + id);
        return (AbstractEntity) query.uniqueResult();
    }

    public Session getSession() {
        return session;
    }

    public Entity getEntity() {
        return entity;
    }
}
