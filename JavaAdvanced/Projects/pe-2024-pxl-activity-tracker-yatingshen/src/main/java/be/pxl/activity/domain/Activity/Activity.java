package be.pxl.activity.domain.Activity;

import be.pxl.activity.domain.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name ="activity")
public class Activity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long activityId;
    private String activity;
    @Column(name = "start_date")
    private LocalDateTime start;
    @Column(name = "end_date")
    private LocalDateTime end;
    private Distance distance;
    @ManyToOne
    private User user;
    @Column(name = "calories_burned")
    private double calories;

    public Activity() {
    }


    public Long getActivityId() {
        return activityId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
    public Duration getDuration() {
        Duration duration = Duration.between(start, end);
        return duration;
    }
}


