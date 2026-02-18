package be.pxl.musicplaylist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<String> handleSongNotFoundException(SongNotFoundException e) {
//        return ResponseEntity.badRequest().body("Song not found");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<String> handleInvalidParameterException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        List<String> errorMessages = new ArrayList<>();
        List<ObjectError> allErrors = e.getAllErrors();
        for (ObjectError error : allErrors) {
            errorMessages.add(error.getDefaultMessage());
        }

        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

}
