package model.entities;

import controller.TaskType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by acer on 17.09.2016.
 */
@Entity
@Table(name = "task", schema = "programming_tutorial", catalog = "")
public class Task extends AbstractEntity implements Serializable{
    private int id;
    private String title;
    private TaskType type;
    private String content;
    private Theme theme;
    private User author;
    private Set<Pupil> pupils = new HashSet<>();

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
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 255)
    @Enumerated(EnumType.STRING)
    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 45)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pupils_and_tasks",
            joinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "pupil_id", referencedColumnName = "id")})
    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (type != task.type) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        if (content != null ? !content.equals(task.content) : task.content != null) return false;
        return  (theme != null ? !theme.equals(task.theme) : task.theme != null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        return result;
    }
}
