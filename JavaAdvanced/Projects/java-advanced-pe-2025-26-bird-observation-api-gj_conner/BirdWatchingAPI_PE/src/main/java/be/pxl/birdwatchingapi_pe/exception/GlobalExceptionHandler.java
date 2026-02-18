package be.pxl.birdwatchingapi_pe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<String>> handleValidation(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getFieldErrors().stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .toList();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ForbiddenActionException.class)
    public ResponseEntity<String> handleForbidden(ForbiddenActionException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}
