package be.pxl.superhero.controller;

import be.pxl.superhero.api.MissionRequest;
import be.pxl.superhero.api.SuperheroDTO;
import be.pxl.superhero.api.SuperheroMissionRequest;
import be.pxl.superhero.api.SuperheroRequest;
import be.pxl.superhero.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superheroes")
public class SuperheroController {

    SuperheroService superheroService;

    @Autowired
    public SuperheroController(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }

    @GetMapping
    public List<SuperheroDTO> getSuperheroes() {
        return superheroService.findAllSuperheroes();
    }

    @PostMapping
    public SuperheroDTO createSuperhero(@RequestBody SuperheroRequest superhero) {
        return superheroService.createSuperhero(superhero);
    }

    @PostMapping("/add-superhero-to-mission")
    public void assignMissionToSuperhero(@RequestBody SuperheroMissionRequest superheroMissionRequest) {
        superheroService.assignMissionToSuperhero(superheroMissionRequest);
    }
}
