package be.pxl.delivery.Exception;

public class InvalidDeliveryIdException extends RuntimeException {
    public InvalidDeliveryIdException(String message)
    {
        super("Delivery ID is invalid");
    }
}
