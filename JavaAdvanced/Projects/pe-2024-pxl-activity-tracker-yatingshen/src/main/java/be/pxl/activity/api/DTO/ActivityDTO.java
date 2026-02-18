package be.pxl.activity.api.DTO;

import be.pxl.activity.domain.Activity.Activity;
import be.pxl.activity.domain.Activity.Distance;
import be.pxl.activity.domain.Activity.DurationToStringFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityDTO {
    private Long activityId;
    private String activity;
    private double calories;
    private LocalDate date;
    private String duration;
    private Distance distance;


    public ActivityDTO(Activity activity){
        this.activityId = activity.getActivityId();
        this.activity = activity.getActivity();
        this.calories = activity.getCalories();

        LocalDate localDate = activity.getStart().toLocalDate();
        this.date = localDate;


        DurationToStringFormatter durationToStringFormatter = new DurationToStringFormatter(activity.getDuration());
        this.duration = durationToStringFormatter.getDuration();
        this.distance = activity.getDistance();
    }
    public ActivityDTO(ActivityStatisticsDTO activityStatisticsDTO){
        this.activityId = activityStatisticsDTO.getActivityId();
        this.activity = activityStatisticsDTO.getActivity();
        this.calories = activityStatisticsDTO.getCalories();
        this.date = activityStatisticsDTO.getStart().toLocalDate();
        this.duration = activityStatisticsDTO.getDuration();
        this.distance = activityStatisticsDTO.getDistance();
    }



    public Long getActivityId() {
        return activityId;
    }


    public String getActivity() {
        return activity;
    }


    public double getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }


    public Distance getDistance() {
        return distance;
    }

}

