package model.entities;

import controller.UserType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by acer on 07.09.2016.
 */
@Entity
@Table(name = "user", schema = "programming_tutorial", catalog = "")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "access")
public class User extends AbstractEntity implements Serializable {
    private int id;
    private String login;
    private String password;
    private UserType access;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Set<Action> actions = new HashSet<>();
    private Set<Theory> theory = new HashSet<>();
    private Set<Task> tasks = new HashSet<>();
    private Set<Task> doneTasks = new HashSet<>();

    public User() {
    }

    public User(String login, String password, UserType access, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.access = access;
        this.firstName = firstName;
        this.lastName = lastName;
    }

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

    @Column(name = "access", nullable = false, insertable=false, updatable=false)
    @Enumerated(EnumType.STRING)
    public UserType getAccess() {
        return access;
    }

    public void setAccess(UserType access) {
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    public Set<Theory> getTheory() {
        return theory;
    }

    public void setTheory(Set<Theory> theory) {
        this.theory = theory;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_and_tasks",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")})
    public Set<Task> getDoneTasks() {
        return doneTasks;
    }


    public void setDoneTasks(Set<Task> doneTasks) {
        this.doneTasks = doneTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (access != user.access) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (patronymic != null ? !patronymic.equals(user.patronymic) : user.patronymic != null) return false;
        if (actions != null ? !actions.equals(user.actions) : user.actions != null) return false;
        if (theory != null ? !theory.equals(user.theory) : user.theory != null) return false;
        return tasks != null ? tasks.equals(user.tasks) : user.tasks == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (access != null ? access.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (actions != null ? actions.hashCode() : 0);
        result = 31 * result + (theory != null ? theory.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }
}
