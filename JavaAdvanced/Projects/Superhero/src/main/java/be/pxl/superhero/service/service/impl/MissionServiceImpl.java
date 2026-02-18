package be.pxl.superhero.service.service.impl;

import be.pxl.superhero.api.MissionDTO;
import be.pxl.superhero.api.MissionRequest;
import be.pxl.superhero.domain.Mission;
import be.pxl.superhero.repository.MissionRepository;
import be.pxl.superhero.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImpl implements MissionService {

    MissionRepository missionRepository;
    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public List<MissionDTO> findAllMissions() {
        return missionRepository.findAll().stream().map(mission -> new MissionDTO(
                mission.getId(),
                mission.getName(),
                mission.isCompleted()
        )).toList();
    }

    @Override
    public MissionDTO createMission(MissionRequest missionDTO) {
        Mission missionEntity = new Mission(missionDTO.getName());
        Mission missionSaved = missionRepository.save(missionEntity);
        return new MissionDTO(
                missionSaved.getId(),
                missionSaved.getName(),
                missionSaved.isCompleted()
        );
    }
}
