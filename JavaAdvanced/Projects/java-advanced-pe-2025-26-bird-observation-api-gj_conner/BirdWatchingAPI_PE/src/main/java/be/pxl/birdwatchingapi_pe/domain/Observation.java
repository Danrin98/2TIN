package be.pxl.birdwatchingapi_pe.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "observations")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int observationId;
    private String speciesCode;
    private String familyCode;
    private String location;
    private LocalDateTime timestamp;
    private String note;
    @ManyToOne
    private User user;

    public Observation() {
    }

    public Observation(String speciesCode, String familyCode, String location, LocalDateTime timestamp, String note) {
        this.speciesCode = speciesCode;
        this.familyCode = familyCode;
        this.location = location;
        this.timestamp = timestamp;
        this.note = note;
    }

    public int getObservationId() {
        return observationId;
    }

    public void setObservationId(int observationId) {
        this.observationId = observationId;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    public String getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(String speciesCode) {
        this.speciesCode = speciesCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        location = location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
