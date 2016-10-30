package model.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by acer on 08.10.2016.
 */
@Entity
@Table(name = "action", schema = "programming_tutorial", catalog = "")
public class Action {
    private int id;
    private int action;
    private Timestamp date;
    private User user;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "action", nullable = false)
    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action1 = (Action) o;

        if (id != action1.id) return false;
        if (action != action1.action) return false;
        if (date != null ? !date.equals(action1.date) : action1.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + action;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
