package be.pxl.birdwatchingapi_pe.controller;

import be.pxl.birdwatchingapi_pe.api.FamilyObservationsDto;
import be.pxl.birdwatchingapi_pe.api.UserStatisticsDto;
import be.pxl.birdwatchingapi_pe.api.UserUpdateDto;
import be.pxl.birdwatchingapi_pe.api.UserRequest;
import be.pxl.birdwatchingapi_pe.service.UserService;
import be.pxl.birdwatchingapi_pe.service.UserStatisticsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserStatisticsService userStatisticsService;


    public UserController(UserService userService, UserStatisticsService userStatisticsService) {
        this.userService = userService;
        this.userStatisticsService = userStatisticsService;
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@Valid @RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDto userUpdateDto, @PathVariable Long id) {
        try {
            userService.updateUser(userUpdateDto, id);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(Principal principal) {
        String email = principal.getName();
        UserStatisticsDto userStatistics = userStatisticsService.getStatistics(email);
        return ResponseEntity.status(HttpStatus.OK).body(userStatistics);
    }

    @GetMapping("/stats/families")
    public ResponseEntity<?> getObservationsPerFamily() {
        List<FamilyObservationsDto> familyObservationsCountList = userStatisticsService.getObservationCountPerFamily();
        return ResponseEntity.status(HttpStatus.OK).body(familyObservationsCountList);
    }

}
