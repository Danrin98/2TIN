package be.pxl.superhero.service.service.impl;

import be.pxl.superhero.api.SuperheroDTO;
import be.pxl.superhero.api.SuperheroMissionRequest;
import be.pxl.superhero.api.SuperheroRequest;
import be.pxl.superhero.domain.Mission;
import be.pxl.superhero.domain.Superhero;
import be.pxl.superhero.repository.MissionRepository;
import be.pxl.superhero.repository.SuperheroRepository;
import be.pxl.superhero.ResourceNotFoundException;
import be.pxl.superhero.service.SuperheroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperheroServiceImpl implements SuperheroService {

    SuperheroRepository superheroRepository;
    MissionRepository missionRepository;
    @Autowired
    public SuperheroServiceImpl(SuperheroRepository superheroRepository, MissionRepository missionRepository) {
        this.superheroRepository = superheroRepository;
        this.missionRepository = missionRepository;
    }

    @Override
    public List<SuperheroDTO> findAllSuperheroes() {
        return superheroRepository.findAll().stream().map(SuperheroDTO::new).toList();
    }

    @Override
    public SuperheroDTO createSuperhero(SuperheroRequest superhero) {
        // map DTO to domain entity
        Superhero superheroEntity = new Superhero();
        superheroEntity.setFirstName(superhero.getFirstName());
        superheroEntity.setLastName(superhero.getLastName());
        superheroEntity.setSuperheroName(superhero.getSuperheroName());
        // save entity to database
        Superhero savedSuperhero = superheroRepository.save(superheroEntity);
        // map saveEntity back to DTO
        return new SuperheroDTO(savedSuperhero);
    }

    @Transactional
    @Override
    public void assignMissionToSuperhero(SuperheroMissionRequest superheroMissionRequest) {
        Superhero superhero = superheroRepository.findById(superheroMissionRequest.getSuperheroId()).orElseThrow(
                () -> new ResourceNotFoundException("Superhero not found"));
        Mission mission = missionRepository.findById(superheroMissionRequest.getMissionId()).orElseThrow(
                () -> new ResourceNotFoundException("Mission not found"));

        superhero.getMissions().add(mission);


        // Dit is niet nodig want we gebruiken @Transactional
        // superheroRepository.save(superhero);
        // missionRepository.save(mission);
    }
}
