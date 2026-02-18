package be.pxl.activity.api;


import be.pxl.activity.api.DTO.ActivityDTO;
import be.pxl.activity.api.DTO.SportStatisticsDTO;
import be.pxl.activity.api.DTO.UserStatisticsDTO;
import be.pxl.activity.api.request.ActivityRequest;
import be.pxl.activity.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<Long> createActivity(@RequestBody ActivityRequest activityRequest, Principal principal) {
        return new ResponseEntity<>(activityService.createActivity(activityRequest, principal.getName()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAllActivities(Principal principal) {
        List<ActivityDTO> activities = activityService.getAllActivities(principal.getName());
        return ResponseEntity.ok(activities);
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long activityId, Principal principal) {
        String username = principal.getName();
        activityService.deleteActivity(activityId, username);
        return ResponseEntity.ok().build(); // Return 200 OK
    }

    @GetMapping("/statistics")
    public ResponseEntity<UserStatisticsDTO> getstatistiek(Principal principal){
        UserStatisticsDTO statistics = activityService.getUserStatistics(principal.getName());
        return ResponseEntity.ok(statistics);
    }
    
    @GetMapping("/{sport}")
    public ResponseEntity<SportStatisticsDTO> getSportStatistics( @PathVariable String sport, Principal principal) {
        String username = principal.getName();
        SportStatisticsDTO statistics = activityService.getSportStatistics(sport, username);
        return ResponseEntity.ok(statistics);
    }
}

