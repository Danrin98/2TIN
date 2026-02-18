package be.pxl.cargo.api;

import be.pxl.cargo.api.request.CreateCargoRequest;
import be.pxl.cargo.api.response.CargoStatistics;
import be.pxl.cargo.service.CargoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    public ResponseEntity<String> addCargo(@RequestBody @Valid CreateCargoRequest createCargoRequest) {
        String code = cargoService.createCargo(createCargoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(code);
    }

    @GetMapping("/statistics")
    public ResponseEntity<CargoStatistics> getStatistics() {
        CargoStatistics statistics = cargoService.getCargoStatistics();
        return ResponseEntity.ok(statistics);
    }
}
