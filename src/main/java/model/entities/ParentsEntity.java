package model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by acer on 07.09.2016.
 */
@Entity
@Table(name = "parents", schema = "programming_tutorial", catalog = "")
public class ParentsEntity extends AbstractEntity implements Serializable {
    private int userId;
    private int child;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "child", nullable = false)
    @Id
    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParentsEntity that = (ParentsEntity) o;

        if (userId != that.userId) return false;
        if (child != that.child) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + child;
        return result;
    }
}
