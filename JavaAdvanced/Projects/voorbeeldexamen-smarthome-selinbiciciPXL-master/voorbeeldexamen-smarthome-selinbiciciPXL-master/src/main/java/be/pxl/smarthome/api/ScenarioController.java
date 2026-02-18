package be.pxl.smarthome.api;


import be.pxl.smarthome.api.request.ActionRequest;
import be.pxl.smarthome.api.request.ScenarioRequest;
import be.pxl.smarthome.exception.ScenarioNameConflictException;
import be.pxl.smarthome.service.ScenarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/scenarios")
public class ScenarioController {

    private final ScenarioService scenarioService;


    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }


    //Als gebruiker geef ik de naam van het nieuwe scenario.
    //Validatie toegevoegd in request scenario dus @Valid
    @PostMapping
    public ResponseEntity<Void> createScenario( @RequestBody @Valid ScenarioRequest scenarioRequest){
            scenarioService.createScenario(scenarioRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Als gebruiker kan ik een actie toevoegen aan een scenario.
    @PutMapping("/{scenarioId}/actions")
    public ResponseEntity<Void> addActionToScenario(@PathVariable Integer scenarioId, @RequestBody ActionRequest actionRequest ){
        scenarioService.addAction(scenarioId, actionRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{scenarioId}/trigger")
    public ResponseEntity<Void> triggerScenario(@PathVariable Integer scenarioId){
        scenarioService.triggers(scenarioId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }




}
