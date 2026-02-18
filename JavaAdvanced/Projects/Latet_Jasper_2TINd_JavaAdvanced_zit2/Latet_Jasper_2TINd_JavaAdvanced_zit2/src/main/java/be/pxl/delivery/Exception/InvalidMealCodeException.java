package be.pxl.delivery.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Ik kan ook een GlobalExceptioHandler gebruiken maar dan is dit nutteloos,
//Uiteindelijk is het enigste verschil dat ik in properties het volgende moet zetten:
//Server.error.include-message=always
//Als ik dat hier niet doe krijg ik geen message terug. Daarom gebruik ik dus een GlobalExceptioHandler
@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidMealCodeException extends RuntimeException {
    public InvalidMealCodeException() {

        super("Maaltijd met opgegeven code bestaat al.");
    }
    public InvalidMealCodeException(String message) {

        super("Maaltijd code niet gevonden");
    }
}
