package be.pxl.smarthome.api;

import be.pxl.smarthome.api.request.ActionRequest;
import be.pxl.smarthome.api.request.ScenarioRequest;
import be.pxl.smarthome.domain.Scenario;
import be.pxl.smarthome.service.ScenarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "scenarios")
public class ScenarioController {

    private final ScenarioService scenarioService;

    @Autowired
    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @PostMapping
    public ResponseEntity<Void> createScenario(@RequestBody @Valid ScenarioRequest scenarioRequest) {
        scenarioService.createScenario(scenarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{scenarioId}/actions")
    public ResponseEntity<Void> addActionToScenario(@PathVariable int scenarioId, @RequestBody ActionRequest actionRequest) {
        scenarioService.addAction(scenarioId, actionRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{scenarioId}/triggers")
    public ResponseEntity<Void> triggerScenario(@PathVariable int scenarioId) {
        scenarioService.triggers(scenarioId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
