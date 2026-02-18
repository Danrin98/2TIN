package be.pxl.student.eventplatform.api;

import be.pxl.student.eventplatform.domain.RegistrationStatus;

import java.time.LocalDateTime;

public class RegistrationRequest {
    public RegistrationStatus status;
    public String fullName;
    public String email;
    public String eventName;
    public LocalDateTime createdAt;
}
