package be.pxl.superhero.controller;

import be.pxl.superhero.api.MissionDTO;
import be.pxl.superhero.api.MissionRequest;
import be.pxl.superhero.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {

    private MissionService missionService;
    @Autowired
    public void setMissionService(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public List<MissionDTO> getMissions() {
        return missionService.findAllMissions();
    }

    @PostMapping
    public MissionDTO createMission(@RequestBody MissionRequest missionRequest) {
        return missionService.createMission(missionRequest);
    }
}
