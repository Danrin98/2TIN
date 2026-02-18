package be.pxl.activity.api.DTO;

import be.pxl.activity.domain.User.User;
import jakarta.persistence.Column;

public class UserDTO {
    private String name;
    private String email;
    private UserDetailDTO userDetailDTO;

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.userDetailDTO = new UserDetailDTO(user.getUserDetails());
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailDTO getUserDetailDTO() {
        return userDetailDTO;
    }

}
