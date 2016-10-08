package model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by acer on 13.09.2016.
 */
@Entity
@Table(name = "parents", schema = "programming_tutorial", catalog = "")
public class Parent extends AbstractEntity implements Serializable {
    private int id;
    private User user;
    private Set<Pupil> children = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @OneToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pupils_and_parents",
            joinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "pupil_id", referencedColumnName = "id")})
    public Set<Pupil> getChildren() {
        return children;
    }

    public void setChildren(Set<Pupil> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent that = (Parent) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return children != null ? children.equals(that.children) : that.children == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }
}
