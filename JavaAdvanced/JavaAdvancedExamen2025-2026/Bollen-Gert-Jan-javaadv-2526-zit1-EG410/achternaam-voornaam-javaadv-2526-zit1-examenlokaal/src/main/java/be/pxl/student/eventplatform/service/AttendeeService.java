package be.pxl.student.eventplatform.service;

//TODO vul de klasse aan

import be.pxl.student.eventplatform.api.AttendeeRequest;
import be.pxl.student.eventplatform.domain.Attendee;
import be.pxl.student.eventplatform.exception.DuplicateEmailException;
import be.pxl.student.eventplatform.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public AttendeeRequest createAttendee(AttendeeRequest attendeeRequest) {
        if  (attendeeRepository.findByEmail(attendeeRequest.email) != null) {
            throw new DuplicateEmailException(attendeeRequest.email);
        }
        Attendee attendee = new Attendee();
        attendee.setEmail(attendeeRequest.email);
        attendee.setFullName(attendeeRequest.fullName);
        attendeeRepository.save(attendee);
        return attendeeRequest;
    }

}
