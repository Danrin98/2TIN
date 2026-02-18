package be.pxl.cargo.service;

import be.pxl.cargo.domain.Shipment;
import be.pxl.cargo.repository.CargoRepository;
import be.pxl.cargo.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final CargoRepository cargoRepository;

    public ShipmentService(ShipmentRepository shipmentRepository, CargoRepository cargoRepository) {
        this.shipmentRepository = shipmentRepository;
        this.cargoRepository = cargoRepository;
    }

    public void addCargoToShipment(Long shipmentId, String cargoCode) {
        // TODO zoek het shipment adhv de primaire sleutel
        // TODO zoek de cargo adhv de unieke code
        // TODO gebruik de methode addCargo in de klasse Shipment om de cargo aan het shipment toe te voegen
        // TODO zorg dat alle wijzigingen correct worden opgeslagen in de database
    }

    public void markShipmentAsArrived(Long shipmentId) {
        Shipment shipment = null; // TODO: zoek het shipment op in de database adhv zijn primaire sleutel
        shipment.setArrived(); // TODO: vervolledig setArrived in de klasse Shipment
        // TODO zorg dat alle wijzigingen correct worden opgeslagen in de database
    }
}
