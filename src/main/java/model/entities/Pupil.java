package model.entities;

import controller.UserType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by acer on 08.10.2016.
 */

@Entity
@DiscriminatorValue("PUPIL")
public class Pupil extends User {
    private int clazz;
    private School school;
    private Set<Theme> themes = new HashSet<>();
    private Set<Parent> parents = new HashSet<>();
    private Set<Teacher> teachers = new HashSet<>();

    public Pupil() {
    }

    public Pupil(String login, String password, UserType access, String firstName, String lastName) {
        super(login, password, access, firstName, lastName);
    }

    @Basic
    @Column(name = "class")
    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
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
}
