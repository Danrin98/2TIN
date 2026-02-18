package be.pxl.musicplaylist.exception;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException() {
    }

    public SongNotFoundException(Throwable cause) {
        super(cause);
    }

    public SongNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SongNotFoundException(String message) {
        super(message);
    }
}
