package be.pxl.delivery.domain;

import be.pxl.delivery.Exception.InValidDeliveryException;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// User story 2 + 3: voeg hier de gevraagde testen toe
public class DeliveryTest {

    @Test
    public void testIsCompleted(){
        Delivery delivery = new Delivery();
        delivery.setMaxMealCount(22);
        delivery.setStartLocation(Location.CENTER);
        Meal meal = new Meal("Pizza", "jasper", Location.CENTER, Location.WEST, delivery);

        delivery.addMeal(meal);
        meal.setStatus(MealStatus.DELIVERED);
        boolean result = delivery.isCompleted();

        Assertions.assertTrue(result);
    }


    @Test
    public void addMeal(){
        Delivery delivery = new Delivery();
        delivery.setMaxMealCount(1);
        delivery.setStartLocation(Location.WEST);
        Meal meal = new Meal("Pizza", "jasper", Location.WEST, Location.EAST, delivery);

        InValidDeliveryException ex = assertThrows(InValidDeliveryException.class,
                ()-> {        delivery.addMeal(meal);});
        assertEquals("Meal has invalid origin", ex.getMessage());
    }
}
