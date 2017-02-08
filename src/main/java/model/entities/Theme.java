package model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by acer on 07.09.2016.
 */
@Entity
@Table(name = "theme", schema = "programming_tutorial", catalog = "")
public class Theme extends AbstractEntity implements Serializable {
    private int id;
    private String title;
    private int clazz;
    private Set<Theory> theory = new HashSet<>();
    private Set<Task> tasks = new HashSet<>();
    private List<Pupil> pupils = new ArrayList<>();

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
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "class", nullable = false)
    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme")
    public Set<Theory> getTheory() {
        return theory;
    }

    public void setTheory(Set<Theory> theory) {
        this.theory = theory;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme")
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pupils_and_themes",
            joinColumns = {@JoinColumn(name = "theme_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "pupil_id", referencedColumnName = "id")})
    public List<Pupil> getPupils() {
        Collections.sort(pupils, new Comparator<Pupil>() {
            @Override
            public int compare(Pupil o1, Pupil o2) {
                if (o1.getDoneTasks().size() > o2.getDoneTasks().size())
                    return -1;
                if (o1.getDoneTasks().size() < o2.getDoneTasks().size())
                    return 1;
                return 0;
            }
        });
        return pupils;
    }

    public void setPupils(List<Pupil> pupils) {
        this.pupils = pupils;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theme that = (Theme) o;

        if (id != that.id) return false;
        if (clazz != that.clazz) return false;
        return  (title != null ? !title.equals(that.title) : that.title != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + clazz;
        return result;
    }
}
