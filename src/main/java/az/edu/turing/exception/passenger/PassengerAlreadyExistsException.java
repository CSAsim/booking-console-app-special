package az.edu.turing.exception.passenger;

import az.edu.turing.enums.PassengerErrorMessage;

public class PassengerAlreadyExistsException extends RuntimeException{

    private final int statusCode;
    private final String message;

    public PassengerAlreadyExistsException(PassengerErrorMessage message) {
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
