package be.pxl.delivery.Exception;

public class MealCodeDoesNotExistException extends RuntimeException {
    public MealCodeDoesNotExistException()
    {
        super("Meal code niet gevonden");
    }
}
