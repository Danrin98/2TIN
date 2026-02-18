package be.pxl.birdwatchingapi_pe.api;

import be.pxl.birdwatchingapi_pe.domain.User;

public class UserDto {
    private String name;
    private String email;
    private UserStatisticsDto userStatisticsDto;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
