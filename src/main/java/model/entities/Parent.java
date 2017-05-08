package model.entities;

import controller.UserType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by acer on 08.10.2016.
 */
@Entity
@DiscriminatorValue("PARENT")
public class Parent extends User {
    private Set<Pupil> pupils = new HashSet<>();

    public Parent() {
    }

    public Parent(String login, String password, UserType access, String firstName, String lastName) {
        super(login, password, access, firstName, lastName);
    }

    @ManyToMany()
    @JoinTable(name = "pupils_and_parents",
            joinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "pupil_id", referencedColumnName = "id")})
    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }
}
