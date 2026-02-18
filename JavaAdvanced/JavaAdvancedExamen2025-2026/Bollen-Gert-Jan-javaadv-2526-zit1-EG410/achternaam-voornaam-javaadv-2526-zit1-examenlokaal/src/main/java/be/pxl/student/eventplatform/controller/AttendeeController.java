package be.pxl.student.eventplatform.controller;

//TODO vul de klasse aan en implementeer de endpoints zoals beschreven in de opdracht

import be.pxl.student.eventplatform.api.AttendeeRequest;
import be.pxl.student.eventplatform.service.AttendeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    //TODO USER STORY 2 — Attendee aanmaken
    @PostMapping
    public ResponseEntity<AttendeeRequest> createAttendee(@Valid @RequestBody AttendeeRequest attendeeRequest) {
        AttendeeRequest attendee = attendeeService.createAttendee(attendeeRequest);
        return ResponseEntity.ok(attendee);
    }
}
