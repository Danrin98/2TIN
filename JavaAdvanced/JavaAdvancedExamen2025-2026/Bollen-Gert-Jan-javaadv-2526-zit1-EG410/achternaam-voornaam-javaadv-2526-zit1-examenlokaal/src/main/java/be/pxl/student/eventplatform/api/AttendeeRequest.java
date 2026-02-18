package be.pxl.student.eventplatform.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AttendeeRequest {
    @Email
    @NotBlank(message = "email cannot be empty")
    public String email;
    @NotBlank(message = "fullname cannot be empty")
    public String fullName;
}
