package be.pxl.birdwatchingapi_pe.api;

import java.time.LocalDateTime;

public class MostObservedSpecies {
    private String speciesCode;
    private String familyCode;
    private int numberOfObservations;
    private LocalDateTime latestObservation;

    public MostObservedSpecies(String speciesCode, String familyCode, int numberOfObservations, LocalDateTime latestObservation) {
        this.speciesCode = speciesCode;
        this.familyCode = familyCode;
        this.numberOfObservations = numberOfObservations;
        this.latestObservation = latestObservation;
    }

    public MostObservedSpecies() {

    }

    public String getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(String speciesCode) {
        this.speciesCode = speciesCode;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    public int getNumberOfObservations() {
        return numberOfObservations;
    }

    public void setNumberOfObservations(int numberOfObservations) {
        this.numberOfObservations = numberOfObservations;
    }

    public LocalDateTime getLatestObservation() {
        return latestObservation;
    }

    public void setLatestObservation(LocalDateTime latestObservation) {
        this.latestObservation = latestObservation;
    }
}
