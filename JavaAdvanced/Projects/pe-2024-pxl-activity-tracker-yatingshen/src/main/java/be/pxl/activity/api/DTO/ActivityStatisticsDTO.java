package be.pxl.activity.api.DTO;

import be.pxl.activity.domain.Activity.Activity;
import be.pxl.activity.domain.Activity.Distance;
import be.pxl.activity.domain.Activity.DurationToStringFormatter;

import java.time.LocalDateTime;

public class ActivityStatisticsDTO {
    private Long activityId;
    private String activity;
    private double calories;
    private LocalDateTime start;
    private LocalDateTime end;
    private String duration;
    private Distance distance;


    public ActivityStatisticsDTO(Activity activity){
        this.activityId = activity.getActivityId();
        this.activity = activity.getActivity();
        this.calories = activity.getCalories();
        this.start = activity.getStart();
        this.end = activity.getEnd();

        DurationToStringFormatter durationToStringFormatter = new DurationToStringFormatter(activity.getDuration());
        this.duration = durationToStringFormatter.getDuration();
        this.distance = activity.getDistance();
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}
