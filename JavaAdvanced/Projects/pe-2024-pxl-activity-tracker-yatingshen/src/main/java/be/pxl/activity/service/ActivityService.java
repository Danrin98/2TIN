package be.pxl.activity.service;

import be.pxl.activity.api.DTO.*;
import be.pxl.activity.api.request.ActivityRequest;
import be.pxl.activity.domain.Activity.Activity;
import be.pxl.activity.domain.Activity.Distance;
import be.pxl.activity.domain.Activity.DurationToStringFormatter;
import be.pxl.activity.domain.Activity.StringToDurationFormatter;
import be.pxl.activity.domain.User.User;
import be.pxl.activity.exception.ActivityRequestValidator;
import be.pxl.activity.exception.ForbiddenActionException;
import be.pxl.activity.exception.ResourceNotFoundException;
import be.pxl.activity.repository.ActivityRepository;
import be.pxl.activity.repository.UserRepository;
import org.springframework.stereotype.Service;
import be.pxl.activity.config.NutritionixConfig;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final NutritionixConfig nutritionixConfig;

    public ActivityService(ActivityRepository activityRepository, UserRepository userRepository, NutritionixConfig nutritionixConfig) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.nutritionixConfig = nutritionixConfig;
    }

    public Long createActivity(ActivityRequest activityRequest, String email){
        User user = userRepository.findByEmail(email);
        double userWeight = user.getUserDetails().getWeight_kg();
        double userHeight = user.getUserDetails().getHeight_cm();
        int userAge = user.getUserDetails().getAge();

        ActivityRequestValidator.validate(activityRequest);

        Activity activity = new Activity();
        activity.setUser(user);
        activity.setActivity(activityRequest.getActivity());
        activity.setStart(activityRequest.getStart());
        activity.setEnd(activityRequest.getEnd());
        double durationInMinutes = (double) java.time.Duration.between(activity.getStart(), activity.getEnd()).toMinutes();
        double calories = calculateCalories(activity.getActivity(), durationInMinutes, userWeight, userHeight, userAge);
        activity.setCalories(calories);
        activity.setDistance(activityRequest.getDistance());

        Activity newActivity = activityRepository.save(activity);

        return newActivity.getActivityId();
    }
    public List<ActivityDTO> getAllActivities(String email){
        User user = userRepository.findByEmail(email);

        List<Activity> activities = activityRepository.findActivitiesByUserId(user.getId());
        List<ActivityDTO> activityDTOList = new ArrayList<>();

        // Verwerk elke activiteit en bereken de calorieën
        for (Activity activity : activities) {
            ActivityDTO dto = new ActivityDTO(activity);
            activityDTOList.add(dto);
        }
        return activityDTOList;
    }
    public void deleteActivity(Long activityId, String username) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + activityId));

        if (!activity.getUser().getEmail().equals(username)) {
            throw new ForbiddenActionException("You are not allowed to delete this activity.");
        }

        activityRepository.delete(activity);
    }
    public UserStatisticsDTO getUserStatistics(String email){
        User user = userRepository.findByEmail(email);

        List<Activity> activities = activityRepository.findActivitiesByUserId(user.getId());
        List<ActivityStatisticsDTO> activityStatisticsDTOList = new ArrayList<>();

        for (Activity activity : activities) {
            ActivityStatisticsDTO dto = new ActivityStatisticsDTO(activity);
            activityStatisticsDTOList.add(dto);
        }

        int totalActivities = activityStatisticsDTOList.size();
        double totalCalories = activityStatisticsDTOList.stream()
                .mapToDouble(ActivityStatisticsDTO::getCalories)
                .sum();

        Map<String, Long> activityCount = activityStatisticsDTOList.stream()
                .collect(Collectors.groupingBy(ActivityStatisticsDTO::getActivity, Collectors.counting()));


        ActivityStatisticsDTO heaviestActivity = activityStatisticsDTOList.stream()
                .max((a, b) -> Double.compare(a.getCalories(), b.getCalories()))
                .orElse(null);
        ActivityDTO heaviest = new ActivityDTO(heaviestActivity);

        ActivityStatisticsDTO longestActivity = activityStatisticsDTOList.stream()
                .max((a, b) -> a.getDuration().compareTo(b.getDuration()))
                .orElse(null);
        ActivityDTO longest = new ActivityDTO(longestActivity);

        String earliest = activityStatisticsDTOList.stream()
                .map(ActivityStatisticsDTO::getStart)
                .min(LocalDateTime::compareTo)
                .map(start -> start.toLocalTime().toString())
                .orElse(null);
        String latest = activityStatisticsDTOList.stream()
                .map(ActivityStatisticsDTO::getStart)
                .max(LocalDateTime::compareTo)
                .map(start -> start.toLocalTime().toString())
                .orElse(null);

        return new UserStatisticsDTO(totalActivities, totalCalories, activityCount,
                heaviest, longest, earliest, latest);
    }
    public SportStatisticsDTO getSportStatistics(String sport, String email){
        User user = userRepository.findByEmail(email);

        List<Activity> activities = activityRepository.findActivitiesByUserId(user.getId());
        List<ActivityStatisticsDTO> activityDTOList = new ArrayList<>();

        Distance distance = new Distance();

        for (Activity activity : activities) {
            if (activity.getActivity().equals(sport)){
                ActivityStatisticsDTO dto = new ActivityStatisticsDTO(activity);
                distance = distance.add(activity.getDistance());
                activityDTOList.add(dto);
            }
        }

        if (activities.isEmpty()) {
            throw new ResourceNotFoundException("No activities found for sport: " + sport);
        }

        int totalActivities = activityDTOList.size();
        double totalCalories = activityDTOList.stream()
                .mapToDouble(ActivityStatisticsDTO::getCalories)
                .sum();

        Duration totalDuration = activityDTOList.stream()
                .map(ActivityStatisticsDTO::getDuration) // Get duration as String
                .map(duration -> new StringToDurationFormatter(duration).parse()) // Create instance and parse
                .reduce(Duration.ZERO, Duration::plus); // Sum durations

        DurationToStringFormatter durationToStringFormatter = new DurationToStringFormatter(totalDuration);
        String formattedDuration = durationToStringFormatter.getDuration();

        return new SportStatisticsDTO(sport,totalActivities,totalCalories,distance,formattedDuration);
    }
    public double calculateCalories(String activity, double duration, double weight_kg, double height_cm, int age) {
        String query = activity + " for " + duration + " hour";

        NutritionixDTO nutritionixDTO = new NutritionixDTO(query, weight_kg, height_cm, age); // Adjust gender as needed

        WebClient webClient = WebClient.builder().build();

        try {
            String responseBody = webClient.post()
                    .uri(nutritionixConfig.getApiUrl())
                    .header("x-app-id", nutritionixConfig.getAppId())
                    .header("x-app-key", nutritionixConfig.getAppKey())
                    .header("Content-Type", "application/json")
                    .bodyValue(nutritionixDTO)
                    .exchangeToMono(response -> {
                            return response.bodyToMono(String.class);
                    })
                    .block();

            if (responseBody == null) {
                throw new RuntimeException("Response body is null");
            }

            String caloriesSubstring = "\"nf_calories\":";
            int startIndex = responseBody.indexOf(caloriesSubstring);
            if (startIndex != -1) {
                int endIndex = responseBody.indexOf(",", startIndex);
                if (endIndex == -1) {
                    endIndex = responseBody.indexOf("}", startIndex);
                }
                String caloriesString = responseBody.substring(startIndex + caloriesSubstring.length(), endIndex).trim();
                return Double.parseDouble(caloriesString);
            }

            throw new RuntimeException("Calories not found in response");

        } catch (Exception e) {
            throw new RuntimeException("Error during request or response parsing: " + e.getMessage(), e);
        }
    }
}



