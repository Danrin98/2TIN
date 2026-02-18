package be.pxl.birdwatchingapi_pe.controller;

import be.pxl.birdwatchingapi_pe.api.ObservationRequest;
import be.pxl.birdwatchingapi_pe.api.ObservationResponse;
import be.pxl.birdwatchingapi_pe.exception.ForbiddenActionException;
import be.pxl.birdwatchingapi_pe.service.ObservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/observations")
public class ObservationController {

    ObservationService observationService;
    @Autowired
    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    @PostMapping
    public ResponseEntity<ObservationResponse> createObservation(@RequestBody @Valid ObservationRequest observation, Principal principal) {
        ObservationResponse response = observationService.createObservation(observation, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<ObservationResponse>> getAllObservationsForUser(Principal principal) {
        List<ObservationResponse> response = observationService.getAllObservationsForUser(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteObservation(@PathVariable int id, Principal principal) {
        try {
            observationService.DeleteObservation(id, principal.getName());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (ForbiddenActionException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

}
