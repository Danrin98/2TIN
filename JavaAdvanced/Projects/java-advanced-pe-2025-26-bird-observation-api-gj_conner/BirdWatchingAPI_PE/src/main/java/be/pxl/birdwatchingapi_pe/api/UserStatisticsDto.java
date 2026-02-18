package be.pxl.birdwatchingapi_pe.api;

import java.util.List;

public class UserStatisticsDto {
    private int totalObservations;
    private int uniqueSpecies;
    private int uniqueFamilies;
    private String averageTimeBetweenObservations;
    private MostObservedSpecies mostObservedSpecies;
    private List<MostObservedFamily> mostObservedFamily;

    public UserStatisticsDto() {}

    public UserStatisticsDto(int totalObservations, int uniqueSpecies, int uniqueFamilies, String averageTimeBetweenObservations, MostObservedSpecies mostObservedSpecies, List<MostObservedFamily> mostObservedFamily) {
        this.totalObservations = totalObservations;
        this.uniqueSpecies = uniqueSpecies;
        this.uniqueFamilies = uniqueFamilies;
        this.averageTimeBetweenObservations = averageTimeBetweenObservations;
        this.mostObservedSpecies = mostObservedSpecies;
        this.mostObservedFamily = mostObservedFamily;
    }

    public int getTotalObservations() {
        return totalObservations;
    }

    public void setTotalObservations(int totalObservations) {
        this.totalObservations = totalObservations;
    }

    public int getUniqueSpecies() {
        return uniqueSpecies;
    }

    public void setUniqueSpecies(int uniqueSpecies) {
        this.uniqueSpecies = uniqueSpecies;
    }

    public int getUniqueFamilies() {
        return uniqueFamilies;
    }

    public void setUniqueFamilies(int uniqueFamilies) {
        this.uniqueFamilies = uniqueFamilies;
    }

    public String getAverageTimeBetweenObservations() {
        return averageTimeBetweenObservations;
    }

    public void setAverageTimeBetweenObservations(String averageTimeBetweenObservations) {
        this.averageTimeBetweenObservations = averageTimeBetweenObservations;
    }

    public MostObservedSpecies getMostObservedSpecies() {
        return mostObservedSpecies;
    }

    public void setMostObservedSpecies(MostObservedSpecies mostObservedSpecies) {
        this.mostObservedSpecies = mostObservedSpecies;
    }

    public List<MostObservedFamily> getMostObservedFamily() {
        return mostObservedFamily;
    }

    public void setMostObservedFamily(List<MostObservedFamily> mostObservedFamily) {
        this.mostObservedFamily = mostObservedFamily;
    }
}
