package be.pxl.student.eventplatform.repository;

import be.pxl.student.eventplatform.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
