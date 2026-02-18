package be.pxl.smarthome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Zorg dat de InvalidDeviceStateException de httpstatus 406 (NOT_ACCEPTABLE) geeft.
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidDeviceStateException extends RuntimeException {
    public InvalidDeviceStateException(String message) {
        super(message);
    }
}
