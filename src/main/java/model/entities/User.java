package model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by acer on 07.09.2016.
 */
@Entity
@Table(name = "users", schema = "programming_tutorial", catalog = "")
public class User extends AbstractEntity implements Serializable {
    private int id;
    private String login;
    private String password;
    private int access;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Pupil pupil;
    private Parent parent;
    private Teacher teacher;
    private Set<Action> actions = new HashSet<>();
    private Set<Theory> theory = new HashSet<>();
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

    @Basic
    @Column(name = "login", nullable = false, length = 255)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "access", nullable = false)
    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "patronymic", nullable = true, length = 255)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Theory> getTheory() {
        return theory;
    }

    public void setTheory(Set<Theory> theory) {
        this.theory = theory;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User entity = (User) o;

        if (id != entity.id) return false;
        if (access != entity.access) return false;
        if (login != null ? !login.equals(entity.login) : entity.login != null) return false;
        if (password != null ? !password.equals(entity.password) : entity.password != null) return false;
        if (firstName != null ? !firstName.equals(entity.firstName) : entity.firstName != null) return false;
        if (lastName != null ? !lastName.equals(entity.lastName) : entity.lastName != null) return false;
        if (patronymic != null ? !patronymic.equals(entity.patronymic) : entity.patronymic != null) return false;
        if (pupil != null ? !pupil.equals(entity.pupil) : entity.pupil != null)
            return false;
        if (parent != null ? !parent.equals(entity.parent) : entity.parent != null)
            return false;
        if (teacher != null ? !teacher.equals(entity.teacher) : entity.teacher != null)
            return false;
        if (actions != null ? !actions.equals(entity.actions) : entity.actions != null) return false;
        if (theory != null ? !theory.equals(entity.theory) : entity.theory != null) return false;
        return tasks != null ? tasks.equals(entity.tasks) : entity.tasks == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + access;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (pupil != null ? pupil.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (actions != null ? actions.hashCode() : 0);
        result = 31 * result + (theory != null ? theory.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }
}
