package be.pxl.cargo.api;

import be.pxl.cargo.service.ShipmentService;

public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }
}
