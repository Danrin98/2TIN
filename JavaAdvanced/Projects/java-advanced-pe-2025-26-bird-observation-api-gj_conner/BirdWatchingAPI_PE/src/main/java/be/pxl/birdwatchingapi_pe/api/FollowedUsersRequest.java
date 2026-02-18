package be.pxl.birdwatchingapi_pe.api;

import jakarta.validation.constraints.NotBlank;

public class FollowedUsersRequest {
    @NotBlank(message = "name cannot be empty")
    private String name;

    public String getName() {
        return name;
    }
}
