package model.entities;

import controller.UserType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by acer on 08.10.2016.
 */
@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends User{
    private Set<Pupil> pupils = new HashSet<>();
    public Teacher() {
    }

    public Teacher(String login, String password, UserType access, String firstName, String lastName) {
        super(login, password, access, firstName, lastName);
    }

    @ManyToMany()
    @JoinTable(name = "pupils_and_teachers",
            joinColumns = {@JoinColumn(name = "teacher_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "pupil_id", referencedColumnName = "id")})
    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }
}
