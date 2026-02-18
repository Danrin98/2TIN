package be.pxl.delivery.api;

import be.pxl.delivery.api.request.CreateMealRequest;
import be.pxl.delivery.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
    // TODO: in deze controller voeg je 1 endpoint toe

    // 1.  Maaltijd toevoegen aan een levering
    // PUT http://{server}:{port}/deliveries/{delivery_id}/meals/{meal_code>}
    @PutMapping("/{deliveryId}/meals/{mealCode}")
    public ResponseEntity AddMealToDelivery(@RequestParam long id, @RequestParam String mealCode){
        deliveryService.addMealToDelivery(id, mealCode);
        return new ResponseEntity<>("Maaltijd toegevoegd aan levering", HttpStatus.OK);

    }
}
