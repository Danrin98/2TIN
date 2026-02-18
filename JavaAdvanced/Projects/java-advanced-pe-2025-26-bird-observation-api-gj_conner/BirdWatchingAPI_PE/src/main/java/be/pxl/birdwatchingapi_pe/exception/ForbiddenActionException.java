package be.pxl.birdwatchingapi_pe.exception;

public class ForbiddenActionException extends RuntimeException {
    public ForbiddenActionException(String message) {
        super(message);
    }
}

