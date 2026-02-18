package be.pxl.delivery.Exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Hidden //Dit moet ik plaatsen voor swaggers anders werkt die niet
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    //Voor InvalidMealCodeException hier ook af te handellen
    @ExceptionHandler(InvalidMealCodeException.class)
    public ResponseEntity<String> InvalidMealCodeException(InvalidMealCodeException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MealCodeDoesNotExistException.class)
    public ResponseEntity<String> MealCodeDoesNotExistException(MealCodeDoesNotExistException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InValidDeliveryException.class)
    public ResponseEntity<String> InValidDeliveryException(InValidDeliveryException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MealOrDeliveryNotFound.class)
    public ResponseEntity<String> MealOrDeliveryNotFound(MealOrDeliveryNotFound ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }


}

