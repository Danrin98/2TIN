package be.pxl.cargo.service;

import be.pxl.cargo.api.request.CreateCargoRequest;
import be.pxl.cargo.api.response.CargoStatistics;
import be.pxl.cargo.domain.Cargo;
import be.pxl.cargo.repository.CargoRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void createCargo(CreateCargoRequest createCargoRequest) {
        // TODO controleer dat de code van de nieuwe cargo uniek is.
        Cargo cargo = new Cargo(createCargoRequest.code(), createCargoRequest.weight(), createCargoRequest.origin(), createCargoRequest.destination());
        cargoRepository.save(cargo);
    }

    public CargoStatistics getCargoStatistics() {
        CargoStatistics cargoStatistics = new CargoStatistics();
        // TODO: Bereken de volgende waarden. Maak hierbij zoveel mogelijk gebruik van de Stream API.
        // TODO 1: Tel het aantal cargo's per cargostatus.
        //  Zet deze resultaten in een map met de cargostatus als key en het aantal cargo's als value.
        //  Vermijd dubbele code!
        // TODO 2: Welke cargo heeft het hoogste gewicht?
        //  Geef van de unieke code van deze cargo.
        // TODO 3: Wat is het gemiddelde gewicht van alle cargo's?
        // TODO 4: Hoeveel cargo's staan momenteel op locatie WAREHOUSE_A?
        // TODO 5: Wat is het totale gewicht van de cargo's op locatie CITY_B met status DELIVERED?
        return cargoStatistics;
    }
}
