package be.pxl.student.eventplatform.service;

//TODO vul de klasse aan

import be.pxl.student.eventplatform.api.RegistrationDto;
import be.pxl.student.eventplatform.api.RegistrationRequest;
import be.pxl.student.eventplatform.domain.Registration;
import be.pxl.student.eventplatform.domain.RegistrationStatus;
import be.pxl.student.eventplatform.exception.NotFoundException;
import be.pxl.student.eventplatform.exception.NotReservedException;
import be.pxl.student.eventplatform.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public RegistrationDto checkIn(long registrationId) {
        Registration registration = registrationRepository.findById(registrationId).orElseThrow();
        if  (registrationRepository.findById(registrationId).isEmpty()) {
            throw new NotFoundException("Registration not found");
        }
        if (registrationRepository.findById(registrationId).get().getStatus() != RegistrationStatus.RESERVED) {
            throw new NotReservedException("Wrong status");
        }
        registrationRepository.findById(registrationId).get().setStatus(RegistrationStatus.CHECKED_IN);
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.status = registration.getStatus();
        registrationDto.attendeeName = registration.getAttendee().getFullName();
        registrationDto.attendeeEmail = registration.getAttendee().getEmail();
        registrationDto.eventName = registration.getEvent().getName();
        registrationDto.checkedInAt =  registration.getCheckedInAt();
        return registrationDto;
    }

}
