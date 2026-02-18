package be.pxl.smarthome.repository;


import be.pxl.smarthome.domain.Device;
import be.pxl.smarthome.domain.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Integer> {
    Optional<Scenario> findByName(String name);
}
