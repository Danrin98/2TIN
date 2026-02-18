package be.pxl.birdwatchingapi_pe.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite species")
public class FavoriteSpecies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String speciesCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FavoriteSpecies() {}

    public FavoriteSpecies(String speciesCode, User user) {
        this.speciesCode = speciesCode;
        this.user = user;
    }

    public Long getId() { return id; }
    public String getSpeciesCode() { return speciesCode; }
    public User getUser() { return user; }
}

