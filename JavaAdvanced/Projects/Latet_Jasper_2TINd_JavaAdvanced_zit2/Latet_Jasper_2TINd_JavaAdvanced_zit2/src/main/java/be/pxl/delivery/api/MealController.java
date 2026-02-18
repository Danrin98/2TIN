package be.pxl.delivery.api;

import be.pxl.delivery.api.request.CreateMealRequest;
import be.pxl.delivery.domain.Meal;
import be.pxl.delivery.service.MealService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meals")
public class MealController {

    private MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }
    // TODO: in deze controller voeg je 3 endpoints toe

    // 1. Maaltijd aanmaken
    // POST http://{server}:{port}/meals
    @PostMapping()
    public ResponseEntity addMeal(@Valid @RequestBody CreateMealRequest mealRequest){
        mealService.createMeal(mealRequest);
        return new ResponseEntity<>("Maaltijd succesvol aangemaakt",HttpStatus.CREATED);
    }
    // 2. Maaltijd afleveren
    // PUT http://localhost:8080/meals/<meal_code>/deliver

    @PutMapping("/{mealCode}/deliver")
    public ResponseEntity deliverMeal(@PathVariable String mealCode){
        return new ResponseEntity<>("Maaltijdstatus gewijzigd naar DELIVERED",HttpStatus.ACCEPTED);

    }
    // 3. Statistieken over maaltijden en leveringen opvragen
    // GET http://localhost:8080/meals/statistics
}
