package be.pxl.student.eventplatform.domain;

//TODO vul de klasse aan zoals beschreven in de opdracht

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime checkedInAt;
    @ManyToOne
    @JoinColumn(name = "attendee_id")
    @NotNull
    private Attendee attendee;
    @ManyToOne
    @JoinColumn(name = "event_id")
    @NotNull
    private Event event;

    public Registration() {}

    public Registration(LocalDateTime checkedInAt, Attendee attendee, Event event) {
        this.status = RegistrationStatus.RESERVED;
        this.createdAt = LocalDateTime.now();
        this.checkedInAt = checkedInAt;
        this.attendee = attendee;
        this.event = event;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCheckedInAt() {
        return checkedInAt;
    }

    public void setCheckedInAt(LocalDateTime checkedInAt) {
        this.checkedInAt = checkedInAt;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }


    //TODO 1 Check-in een registratie
    // Check-in kan enkel wanneer status RESERVED is. Indien niet moet de foutmelding "Check-in not allowed" optreden.
    // Bij check-in wordt status CHECKED_IN en checkedInAt wordt gezet.
    // TIP: Huidige datum en tijd kan bekomen worden via LocalDateTime.now()
    //TODO 2 UNIT TEST deze methode
    public void checkIn() {
        if (this.status == RegistrationStatus.RESERVED) {
            this.setStatus(RegistrationStatus.CHECKED_IN);
            this.setCheckedInAt(LocalDateTime.now());
        }
        else {
            throw new IllegalStateException("Check-in not allowed");
        }
    }

}
