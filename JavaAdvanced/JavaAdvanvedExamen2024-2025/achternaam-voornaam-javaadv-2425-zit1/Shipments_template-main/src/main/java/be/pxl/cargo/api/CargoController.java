package be.pxl.cargo.api;

import be.pxl.cargo.api.request.CreateCargoRequest;
import be.pxl.cargo.service.CargoService;

public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    public void addCargo(CreateCargoRequest createCargoRequest) {
        cargoService.createCargo(createCargoRequest);
    }

}
