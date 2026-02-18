package be.pxl.activity.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is not filled in")
    private String email;
    @NotBlank(message = "Name is not filled in")
    private String name;
    @NotBlank(message = "Password is not filled in")
    private String password;

    // GETTERS AND SETTERS
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

