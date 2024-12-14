package az.edu.turing.exception.passenger;

import az.edu.turing.enums.PassengerErrorMessage;

public class PassengerNotFoundException extends RuntimeException {

    private final int statusCode;
    private final String message;

    public PassengerNotFoundException(PassengerErrorMessage message) {
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
