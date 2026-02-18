package be.pxl.superhero.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "missions")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name= "mission_name")
    private String name;
    private boolean completed;
    private boolean deleted;
    @ManyToMany(mappedBy = "missions")
    private List<Superhero> superheroes;

    public Mission() {}

    public Mission(String name) {
        this.name = name;
        this.completed = false;
        this.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Superhero> getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(List<Superhero> superheroes) {
        this.superheroes = superheroes;
    }
}
