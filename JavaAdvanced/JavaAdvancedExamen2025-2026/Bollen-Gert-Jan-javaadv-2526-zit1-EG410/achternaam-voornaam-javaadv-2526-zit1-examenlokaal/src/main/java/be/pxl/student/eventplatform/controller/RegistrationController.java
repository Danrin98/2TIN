package be.pxl.student.eventplatform.controller;

//TODO vul de klasse aan en implementeer de endpoints zoals beschreven in de opdracht

import be.pxl.student.eventplatform.api.RegistrationDto;
import be.pxl.student.eventplatform.domain.Registration;
import be.pxl.student.eventplatform.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //TODO USER STORY 3 — Inschrijven op een event
    @PutMapping("/{registrationId}/check-in")
    public ResponseEntity<RegistrationDto> checkIn(@PathVariable("registrationId") long registrationId) {
        RegistrationDto registrationDto = registrationService.checkIn(registrationId);
        return ResponseEntity.ok(registrationDto);
    }
}
