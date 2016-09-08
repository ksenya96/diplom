package model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by acer on 07.09.2016.
 */
@Entity
@Table(name = "pupils", schema = "programming_tutorial", catalog = "")
public class PupilsEntity extends AbstractEntity implements Serializable {
    private int id;
    private int userId;
    private int clazz;
    private int teacher;
    private Integer schoolId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "class", nullable = false)
    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }


    @Column(name = "teacher", nullable = false)
    @Id
    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    @Column(name = "school_id", nullable = true)
    @Id
    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PupilsEntity that = (PupilsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (clazz != that.clazz) return false;
        if (teacher != that.teacher) return false;
        if (schoolId != null ? !schoolId.equals(that.schoolId) : that.schoolId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + clazz;
        result = 31 * result + teacher;
        result = 31 * result + (schoolId != null ? schoolId.hashCode() : 0);
        return result;
    }
}
