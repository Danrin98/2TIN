package be.pxl.student.eventplatform.api;

import be.pxl.student.eventplatform.domain.RegistrationStatus;

import java.time.LocalDateTime;

public class RegistrationDto {
    public RegistrationStatus status;
    public String attendeeName;
    public String attendeeEmail;
    public String eventName;
    public LocalDateTime checkedInAt;
}
