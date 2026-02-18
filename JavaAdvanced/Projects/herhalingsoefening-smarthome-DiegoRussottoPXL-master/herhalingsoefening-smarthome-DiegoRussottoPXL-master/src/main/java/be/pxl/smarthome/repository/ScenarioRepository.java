package be.pxl.smarthome.repository;

import be.pxl.smarthome.domain.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Integer> {
}
