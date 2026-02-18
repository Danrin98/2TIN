package be.pxl.student.eventplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotReservedException extends RuntimeException {
    public NotReservedException(String message) {
        super(message);
    }
}
