package be.pxl.activity.api.DTO;

import be.pxl.activity.domain.Activity.Activity;
import be.pxl.activity.domain.User.UserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserStatisticsDTO {
    private final int totalActivities;
    private final double totalCalories;
    private final Map<String, Long> activityCount;
    private final ActivityDTO heaviestActivity;
    private final ActivityDTO longestActivity;
    private final String earliest;
    private final String latest;
    public UserStatisticsDTO(int totalActivities, double totalCalories, Map<String, Long> activityCount,
                             ActivityDTO heaviestActivity, ActivityDTO longestActivity,
                             String earliest, String latest) {
        this.totalActivities = totalActivities;
        this.totalCalories = totalCalories;
        this.activityCount = activityCount;
        this.heaviestActivity = heaviestActivity;
        this.longestActivity = longestActivity;
        this.earliest = earliest;
        this.latest = latest;
    }
    public int getTotalActivities() {
        return totalActivities;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public Map<String, Long> getActivityCount() {
        return activityCount;
    }

    public ActivityDTO getHeaviestActivity() {
        return heaviestActivity;
    }

    public ActivityDTO getLongestActivity() {
        return longestActivity;
    }

    public String getEarliest() {
        return earliest;
    }

    public String getLatest() {
        return latest;
    }

}
