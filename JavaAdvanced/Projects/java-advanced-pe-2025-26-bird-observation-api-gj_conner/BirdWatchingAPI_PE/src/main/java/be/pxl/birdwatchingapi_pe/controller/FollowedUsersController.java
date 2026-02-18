package be.pxl.birdwatchingapi_pe.controller;

import be.pxl.birdwatchingapi_pe.api.FollowedUsersResponse;
import be.pxl.birdwatchingapi_pe.api.FollowedUsersRequest;
import be.pxl.birdwatchingapi_pe.service.FollowedUsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/following")
public class FollowedUsersController {

    FollowedUsersService followedUsersService;
    @Autowired
    public FollowedUsersController(FollowedUsersService followedUsersService) {
        this.followedUsersService = followedUsersService;
    }

    @PostMapping
    public ResponseEntity<?> followUser(@RequestBody @Valid FollowedUsersRequest request, Principal principal) {
        FollowedUsersResponse response = followedUsersService.followUser(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getFollowedUsers(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(followedUsersService.getFollowedUsers(principal.getName()));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteFollowedUser(@PathVariable String name, Principal principal) {
        try {
            followedUsersService.deleteFollowedUser(name, principal.getName());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
