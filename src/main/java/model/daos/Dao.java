package model.daos;

import model.entities.AbstractEntity;
import model.entities.ThemesEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by acer on 11.08.2016.
 */
public abstract class Dao {
    private Session session;
    private Entity entity;

    public Dao(Session session, Entity entity) {
        this.session = session;
        this.entity = entity;
    }

    public void add(AbstractEntity entity) {
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
        Query query = session.createQuery("FROM " + entity.getTableName());
        return query.list();
    }

    public Session getSession() {
        return session;
    }

    public Entity getEntity() {
        return entity;
    }
}
