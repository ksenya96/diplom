package model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by acer on 13.09.2016.
 */
@Entity
@Table(name = "pupils", schema = "programming_tutorial", catalog = "")
public class Pupil extends AbstractEntity implements Serializable {
    private int id;
    private int clazz;
    private User user;
    private School school;
    private Set<Parent> parents = new HashSet<>();
    private Set<Teacher> teachers = new HashSet<>();
    private Set<Theme> themes = new HashSet<>();
    private Set<Task> tasks = new HashSet<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", insertable = false, updatable = false)
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pupils_and_parents",
    joinColumns = {@JoinColumn(name = "pupil_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")})
    public Set<Parent> getParents() {
        return parents;
    }

    public void setParents(Set<Parent> parents) {
        this.parents = parents;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pupils_and_teachers",
            joinColumns = {@JoinColumn(name = "pupil_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id", referencedColumnName = "id")})
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pupils_and_themes",
            joinColumns = {@JoinColumn(name = "pupil_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "theme_id", referencedColumnName = "id")})
    public Set<Theme> getThemes() {
        return themes;
    }

    public void setThemes(Set<Theme> themes) {
        this.themes = themes;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pupils_and_tasks",
            joinColumns = {@JoinColumn(name = "pupil_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")})
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }


    @Basic
    @Column(name = "class", nullable = false)
    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pupil that = (Pupil) o;

        if (id != that.id) return false;
        if (clazz != that.clazz) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null)
            return false;
        if (parents != null ? !parents.equals(that.parents) : that.parents != null) return false;
        if (teachers != null ? !teachers.equals(that.teachers) : that.teachers != null) return false;
        if (themes != null ? !themes.equals(that.themes) : that.themes != null) return false;
        return tasks != null ? tasks.equals(that.tasks) : that.tasks == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clazz;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (parents != null ? parents.hashCode() : 0);
        result = 31 * result + (teachers != null ? teachers.hashCode() : 0);
        result = 31 * result + (themes != null ? themes.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }
}
