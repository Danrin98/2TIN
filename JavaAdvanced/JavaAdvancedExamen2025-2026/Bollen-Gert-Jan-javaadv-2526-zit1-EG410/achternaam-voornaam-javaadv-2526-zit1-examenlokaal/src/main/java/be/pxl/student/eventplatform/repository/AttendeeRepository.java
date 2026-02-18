package be.pxl.student.eventplatform.repository;

import be.pxl.student.eventplatform.domain.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    Attendee findByEmail(String email);
}
