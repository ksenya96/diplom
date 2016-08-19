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
public class ThemesDao implements Dao {
    private Session session;
    private Entity entity;

    public ThemesDao(Session session, Entity entity) {
        this.session = session;
        this.entity = entity;
    }

    @Override
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

    @Override
    public void read() {

    }

    @Override
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

    @Override
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

    @Override
    public List<ThemesEntity> getAllEntities() {
        Query query = session.createQuery("FROM " + entity.getTableName());
        return query.list();
    }

}
