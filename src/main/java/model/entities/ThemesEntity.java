package model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by acer on 11.08.2016.
 */
@Entity
@Table(name = "themes", schema = "programming_tutorial")
public class ThemesEntity extends AbstractEntity implements Serializable {
    private int id;
    private String title;
    private Integer clazz;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "class", nullable = true)
    public Integer getClazz() {
        return clazz;
    }

    public void setClazz(Integer clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThemesEntity that = (ThemesEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ThemesEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
