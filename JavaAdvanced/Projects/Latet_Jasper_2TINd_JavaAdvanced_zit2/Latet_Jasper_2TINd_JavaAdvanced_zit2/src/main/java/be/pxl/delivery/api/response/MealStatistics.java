package be.pxl.delivery.api.response;

import be.pxl.delivery.domain.Location;
import be.pxl.delivery.domain.MealStatus;

import java.util.Map;

public class MealStatistics {
    private Map<MealStatus, Long> statusCounts;
    private String mostDeliveredLocation;
    private String mostPopularDestination;
    private double averageMealsPerDelivery;
    private long completedDeliveries;

    public Map<MealStatus, Long> getStatusCounts() {
        return statusCounts;
    }

    public void setStatusCounts(Map<MealStatus, Long> statusCounts) {
        this.statusCounts = statusCounts;
    }

    public String getMostDeliveredLocation() {
        return mostDeliveredLocation;
    }

    public void setMostDeliveredLocation(String mostDeliveredLocation) {
        this.mostDeliveredLocation = mostDeliveredLocation;
    }

    public String getMostPopularDestination() {
        return mostPopularDestination;
    }

    public void setMostPopularDestination(String mostPopularDestination) {
        this.mostPopularDestination = mostPopularDestination;
    }

    public double getAverageMealsPerDelivery() {
        return averageMealsPerDelivery;
    }

    public void setAverageMealsPerDelivery(double averageMealsPerDelivery) {
        this.averageMealsPerDelivery = averageMealsPerDelivery;
    }

    public long getCompletedDeliveries() {
        return completedDeliveries;
    }

    public void setCompletedDeliveries(long completedDeliveries) {
        this.completedDeliveries = completedDeliveries;
    }
}
