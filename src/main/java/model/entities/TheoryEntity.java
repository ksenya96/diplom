package model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by acer on 20.08.2016.
 */
@Entity
@Table(name = "theory", schema = "programming_tutorial")
public class TheoryEntity extends AbstractEntity implements Serializable {
    private int id;
    private int themeId;
    private String content;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "theme_id", nullable = false, insertable = false, updatable = false)
    @Id
    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TheoryEntity that = (TheoryEntity) o;

        if (id != that.id) return false;
        if (themeId != that.themeId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + themeId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
