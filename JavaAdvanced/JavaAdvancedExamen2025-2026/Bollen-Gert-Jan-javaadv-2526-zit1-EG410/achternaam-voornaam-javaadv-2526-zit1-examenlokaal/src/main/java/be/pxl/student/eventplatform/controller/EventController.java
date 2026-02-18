package be.pxl.student.eventplatform.controller;

//TODO vul de klasse aan en implementeer de endpoints zoals beschreven in de opdracht

import be.pxl.student.eventplatform.api.RegistrationRequest;
import be.pxl.student.eventplatform.api.StatisticsDTO;
import be.pxl.student.eventplatform.domain.Event;
import be.pxl.student.eventplatform.repository.EventRepository;
import be.pxl.student.eventplatform.service.EventService;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //TODO USER STORY 1 - Events raadplegen
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok().body(events);
    }

    //TODO USER STORY 3 -  Inschrijven op een event
    @PutMapping("/{eventId}/registrations")
    public ResponseEntity<RegistrationRequest> registerForEvent(@PathVariable long eventId, String email) {
        RegistrationRequest registrationRequest = eventService.createRegistration(eventId, email);
        return ResponseEntity.ok().body(registrationRequest);
    }

    //TODO USER STORY 5 - Registratiestatistieken opvragen
    @GetMapping("/statistics")
    public ResponseEntity<StatisticsDTO> getStatistics() {
        StatisticsDTO statistics = eventService.getStatistics();
        return ResponseEntity.ok().body(statistics);
    }
}
