package be.pxl.cargo.api;

import be.pxl.cargo.api.request.CreateCargoRequest;
import be.pxl.cargo.service.ShipmentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {

        this.shipmentService = shipmentService;
    }

    @PutMapping("{shipmentId}/cargo/{cargoCode}")
    public ResponseEntity<?> addCargo(@PathVariable Long shipmentId, @PathVariable String cargoCode) {
        shipmentService.addCargoToShipment(shipmentId, cargoCode);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{shipmentId}/arrive")
    public ResponseEntity<?> arrive(@PathVariable Long shipmentId) {
        shipmentService.markShipmentAsArrived(shipmentId);
        return ResponseEntity.accepted().build();
    }
}
