package be.pxl.superhero.api;

import be.pxl.superhero.domain.Superhero;

public class SuperheroDTO {
    private long id;

    private String firstName;
    private String lastName;
    private String superheroName;

    public SuperheroDTO() {}

    public SuperheroDTO(Superhero superhero) {
        this.id = superhero.getId();
        this.firstName = superhero.getFirstName();
        this.lastName = superhero.getLastName();
        this.superheroName = superhero.getSuperheroName();
    }
    public SuperheroDTO(long id, String firstName, String lastName, String superheroName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.superheroName = superheroName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
