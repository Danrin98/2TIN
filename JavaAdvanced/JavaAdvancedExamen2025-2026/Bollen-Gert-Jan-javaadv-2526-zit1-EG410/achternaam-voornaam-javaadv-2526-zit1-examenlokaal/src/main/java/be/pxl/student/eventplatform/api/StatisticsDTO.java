package be.pxl.student.eventplatform.api;

import java.util.Map;

public class StatisticsDTO {
    private Map<String, Long> statusCounts;
    private String mostPopularVenueArea;
    private String topEventByCheckIns;
    private double averageRegistrationsPerEvent;
    private long soldOutEvents;

    public StatisticsDTO() {}

    public Map<String, Long> getStatusCounts() {
        return statusCounts;
    }

    public void setStatusCounts(Map<String, Long> statusCounts) {
        this.statusCounts = statusCounts;
    }

    public String getMostPopularVenueArea() {
        return mostPopularVenueArea;
    }

    public void setMostPopularVenueArea(String mostPopularVenueArea) {
        this.mostPopularVenueArea = mostPopularVenueArea;
    }

    public String getTopEventByCheckIns() {
        return topEventByCheckIns;
    }

    public void setTopEventByCheckIns(String topEventByCheckIns) {
        this.topEventByCheckIns = topEventByCheckIns;
    }

    public double getAverageRegistrationsPerEvent() {
        return averageRegistrationsPerEvent;
    }

    public void setAverageRegistrationsPerEvent(double averageRegistrationsPerEvent) {
        this.averageRegistrationsPerEvent = averageRegistrationsPerEvent;
    }

    public long getSoldOutEvents() {
        return soldOutEvents;
    }

    public void setSoldOutEvents(long soldOutEvents) {
        this.soldOutEvents = soldOutEvents;
    }
}

