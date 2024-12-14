package az.edu.turing.exception.flight;

import az.edu.turing.enums.FLightErrorMessage;

public class FlightNotFoundException extends RuntimeException{

    private final int statusCode;
    private final String message;

    public FlightNotFoundException(FLightErrorMessage message) {
        super(message.getMessage());
        this.statusCode = message.getStatus();
        this.message = message.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
