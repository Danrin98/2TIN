package be.pxl.activity.api.DTO;

import be.pxl.activity.domain.Activity.Activity;
import be.pxl.activity.domain.Activity.Distance;

public class SportStatisticsDTO {
    private final String sport;
    private final int totalActivities;
    private final double totalCalories;
    private final Distance totalDistance;
    private final String totalTime;
    public SportStatisticsDTO(String sport, int totalActivities, double totalCalories, Distance totalDistance, String totalTime) {
        this.sport = sport;
        this.totalActivities = totalActivities;
        this.totalCalories = totalCalories;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
    }

    public String getSport() {
        return sport;
    }

    public int getTotalActivities() {
        return totalActivities;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public Distance getTotalDistance() {
        return totalDistance;
    }

    public String getTotalTime() {
        return totalTime;
    }
}
