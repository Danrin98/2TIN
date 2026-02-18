package be.pxl.delivery.Exception;

public class MealOrDeliveryNotFound extends RuntimeException {
    public MealOrDeliveryNotFound() {
        super(
                "Maaltijd of levering niet gevonden"
        );
    }
}
