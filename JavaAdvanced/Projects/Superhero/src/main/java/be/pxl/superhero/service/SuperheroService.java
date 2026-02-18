package be.pxl.superhero.service;

import be.pxl.superhero.api.SuperheroDTO;
import be.pxl.superhero.api.SuperheroMissionRequest;
import be.pxl.superhero.api.SuperheroRequest;

import java.util.List;

public interface SuperheroService {

    public List<SuperheroDTO> findAllSuperheroes();
    public SuperheroDTO createSuperhero(SuperheroRequest superhero);

    public void assignMissionToSuperhero(SuperheroMissionRequest superheroMissionRequest);
}
