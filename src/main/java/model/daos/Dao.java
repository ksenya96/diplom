package model.daos;

import model.entities.AbstractEntity;

import java.util.List;

/**
 * Created by acer on 11.08.2016.
 */
public interface Dao {
    void add(AbstractEntity entity);
    void read();
    void update(AbstractEntity entity);
    void delete(AbstractEntity entity);
    List<? extends AbstractEntity> getAllEntities();
}
