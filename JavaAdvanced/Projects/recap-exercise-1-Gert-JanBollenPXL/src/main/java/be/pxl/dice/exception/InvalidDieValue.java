package be.pxl.dice.exception;

public class InvalidDieValue extends RuntimeException {
    public InvalidDieValue(String message) {
        super(message);
    }
}
