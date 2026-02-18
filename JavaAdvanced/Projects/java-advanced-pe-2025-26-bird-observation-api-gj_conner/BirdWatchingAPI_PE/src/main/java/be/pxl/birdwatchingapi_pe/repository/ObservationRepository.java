package be.pxl.birdwatchingapi_pe.repository;

import be.pxl.birdwatchingapi_pe.domain.Observation;
import be.pxl.birdwatchingapi_pe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Integer> {
    List<Observation> findAllByOrderByTimestampAsc();
    List<Observation> findAllByUser(User user);
    List<Observation> findAllByUserEmailOrderByTimestampAsc(String email);
}
