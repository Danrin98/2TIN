package be.pxl.superhero.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String superheroName;
    @ManyToMany
    private List<Mission> missions;

    public Superhero() {}

    public Superhero(String firstName, String lastName, String superheroName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.superheroName = superheroName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuperheroName() {
        return superheroName;
    }

    public void setSuperheroName(String superheroName) {
        this.superheroName = superheroName;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }
}
