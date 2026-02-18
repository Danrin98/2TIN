package be.pxl.huizenjacht.rest;

import be.pxl.huizenjacht.api.House;
import be.pxl.huizenjacht.service.HouseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {

    private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

    HouseService houseService;
    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<House> createHouse(@RequestBody @Valid House house) {
        logger.info("Created house: {} as a new house with name: {}", house, house.getName());
        House createdHouse = houseService.createHouse(house);
        return ResponseEntity.ok(createdHouse);
    }

    @GetMapping 
    public List<House> getAllHouses() {
        return houseService.getHouses();
    }

    @PutMapping("/{code}")
    public House updateHouse(@PathVariable String code, @RequestBody @Valid House house) {
        return houseService.updateHouse(code, house);
    }
    @PutMapping("/{code}/sold")
    public House updateHouseSOLD(@PathVariable String code) {
        return houseService.updateHouseSOLD(code);
    }

    @DeleteMapping("{code}")
    public ResponseEntity<Void> deleteHouse(@PathVariable String code) {
        houseService.deleteHouse(code);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
