package be.pxl.cargo.service;

import be.pxl.cargo.api.exceptions.InvalidCodeException;
import be.pxl.cargo.api.request.CreateCargoRequest;
import be.pxl.cargo.api.response.CargoStatistics;
import be.pxl.cargo.domain.Cargo;
import be.pxl.cargo.domain.CargoStatus;
import be.pxl.cargo.domain.Location;
import be.pxl.cargo.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public String createCargo(CreateCargoRequest createCargoRequest) {
        // TODO controleer dat de code van de nieuwe cargo uniek is.
        if (cargoRepository.findByCode(createCargoRequest.code()) != null)
            throw new InvalidCodeException("Er bestaat reeds een cargo met code: " + createCargoRequest.code());
        Cargo cargo = new Cargo(createCargoRequest.code(), createCargoRequest.weight(), createCargoRequest.origin(), createCargoRequest.destination());
        cargoRepository.save(cargo);
        return cargo.getCode();
    }

    public CargoStatistics getCargoStatistics() {
        CargoStatistics cargoStatistics = new CargoStatistics();
        List<Cargo> cargos = cargoRepository.findAll();

        // TODO: Bereken de volgende waarden. Maak hierbij zoveel mogelijk gebruik van de Stream API.
        // TODO 1: Tel het aantal cargo's per cargostatus.
        //  Zet deze resultaten in een map met de cargostatus als key en het aantal cargo's als value.
        //  Vermijd dubbele code!
        Map<CargoStatus, Long> cargoCount = cargos.stream()
                .collect(Collectors.groupingBy(Cargo::getCargoStatus, Collectors.summingLong(e -> 1)));
        cargoStatistics.setStatusCount(cargoCount);

        // TODO 2: Welke cargo heeft het hoogste gewicht?
        //  Geef van de unieke code van deze cargo.
        Cargo heaviestCargo = cargos.stream()
                .max(Comparator.comparingDouble(Cargo::getWeight))
                .orElse(null);
        cargoStatistics.setHeaviestCargo(heaviestCargo.getCode());

        // TODO 3: Wat is het gemiddelde gewicht van alle cargo's?
        Double averageWeight = cargos.stream()
                .mapToDouble(Cargo::getWeight).sum() / cargos.stream().count();
        cargoStatistics.setAverageCargoWeight(averageWeight);

        // TODO 4: Hoeveel cargo's staan momenteel op locatie WAREHOUSE_A?
        Long cargosAtWareHouseA = cargos.stream()
                .filter(cargo -> cargo.getCurrentLocation() == Location.WAREHOUSE_A)
                .count();
        cargoStatistics.setCountCargosAtWarehouseA(cargosAtWareHouseA);

        // TODO 5: Wat is het totale gewicht van de cargo's op locatie CITY_B met status DELIVERED?
        Double deliveredCargosCityB = cargos.stream()
                .filter(cargo -> cargo.getCurrentLocation() == Location.CITY_B)
                .filter(cargo -> cargo.getCargoStatus() == CargoStatus.DELIVERED)
                .mapToDouble(Cargo::getWeight).sum();
        cargoStatistics.setTotalWeightDeliveredAtCityB(deliveredCargosCityB);

        return cargoStatistics;
    }
}
