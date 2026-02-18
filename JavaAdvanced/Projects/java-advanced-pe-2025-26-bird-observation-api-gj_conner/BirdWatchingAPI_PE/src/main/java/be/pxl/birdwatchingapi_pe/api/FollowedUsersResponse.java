package be.pxl.birdwatchingapi_pe.api;

public class FollowedUsersResponse {
    private String name;

    public FollowedUsersResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
