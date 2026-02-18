package be.pxl.birdwatchingapi_pe.service;

import be.pxl.birdwatchingapi_pe.api.ObservationRequest;
import be.pxl.birdwatchingapi_pe.domain.Observation;
import be.pxl.birdwatchingapi_pe.api.ObservationResponse;
import be.pxl.birdwatchingapi_pe.exception.ForbiddenActionException;
import be.pxl.birdwatchingapi_pe.repository.ObservationRepository;
import be.pxl.birdwatchingapi_pe.repository.UserRepository;
import be.pxl.birdwatchingapi_pe.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ObservationService {

    private final ObservationRepository observationRepository;
    private final EBirdService ebirdService;
    private final UserRepository userRepository;

    public ObservationService(ObservationRepository observationRepository, EBirdService ebirdService, UserRepository userRepository) {
        this.observationRepository = observationRepository;
        this.ebirdService = ebirdService;
        this.userRepository = userRepository;
    }


    public ObservationResponse createObservation(ObservationRequest observation, String email) {

        LocalDateTime timeStamp = observation.getTimeStamp() != null ? observation.getTimeStamp() : LocalDateTime.now();
        String note = observation.getNote() != null ? observation.getNote() : "";

        User user = userRepository.findByEmail(email);

        String familyCode = ebirdService.getFamilyCode(observation.getSpeciesCode());

        Observation newObservation = new Observation(
                observation.getSpeciesCode(),
                familyCode,
                observation.getLocation(),
                timeStamp,
                note
        );
        newObservation.setUser(user);

        Observation saved = observationRepository.save(newObservation);

        return new ObservationResponse(
                saved.getObservationId(),
                saved.getSpeciesCode(),
                saved.getFamilyCode(),
                saved.getLocation(),
                saved.getTimestamp(),
                saved.getNote()
        );
    }

    public List<ObservationResponse> getAllObservationsForUser(String email) {
        User user = userRepository.findByEmail(email);
        return observationRepository.findAllByUser(user)
                .stream()
                .map(observation -> new ObservationResponse(
                        observation.getObservationId(),
                        observation.getSpeciesCode(),
                        observation.getFamilyCode(),
                        observation.getLocation(),
                        observation.getTimestamp(),
                        observation.getNote()))
                .toList();
    }

    public void DeleteObservation(int id, String email) {
        Observation observation = observationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
        String userEmail = observation.getUser().getEmail();
        if (userEmail.equals(email)) {
            observationRepository.deleteById(id);
        } else {
            throw new ForbiddenActionException("Observation is not from this user");
        }
    }
}
