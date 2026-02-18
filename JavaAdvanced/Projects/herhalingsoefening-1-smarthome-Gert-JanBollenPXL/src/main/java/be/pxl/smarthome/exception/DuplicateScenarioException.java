package be.pxl.smarthome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateScenarioException extends RuntimeException {
	public DuplicateScenarioException(String message) {
		super(message);
	}
}
