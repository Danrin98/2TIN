package be.pxl.superhero.service;

import be.pxl.superhero.api.MissionDTO;
import be.pxl.superhero.api.MissionRequest;

import java.util.List;

public interface MissionService {
    List<MissionDTO> findAllMissions();
    MissionDTO createMission(MissionRequest missionDTO);
}
