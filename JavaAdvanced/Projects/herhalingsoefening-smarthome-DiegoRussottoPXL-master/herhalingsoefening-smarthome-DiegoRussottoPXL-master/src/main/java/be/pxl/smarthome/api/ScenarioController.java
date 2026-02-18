package be.pxl.smarthome.api;

import be.pxl.smarthome.api.request.ScenarioRequest;
import be.pxl.smarthome.domain.Scenario;
import be.pxl.smarthome.exception.DuplicateScenarioException;
import be.pxl.smarthome.service.ScenarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "scenarios")
public class ScenarioController {
    private final ScenarioService scenarioService;

    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @PostMapping
    public ResponseEntity<?> createScenario(@Valid @RequestBody ScenarioRequest scenarioRequest){
        try {
            Scenario createdScenario = scenarioService.createScenario(scenarioRequest.name());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdScenario);
        } catch (DuplicateScenarioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("e.g. in case the exact name already exists");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("e.g. in case the name is missing");
        }
    }
}
