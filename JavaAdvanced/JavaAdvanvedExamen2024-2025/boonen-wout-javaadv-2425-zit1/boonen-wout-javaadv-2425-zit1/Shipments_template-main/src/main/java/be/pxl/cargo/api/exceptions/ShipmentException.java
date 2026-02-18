package be.pxl.cargo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShipmentException extends RuntimeException{
    public ShipmentException(String message) {
        super(message);
    }
}
