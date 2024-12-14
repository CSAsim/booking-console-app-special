package az.edu.turing.exception.booking;

import az.edu.turing.enums.BookingErrorMessage;

public class BookingNotFoundException extends RuntimeException{

    private final int statusCode;
    private final String message;

    public BookingNotFoundException(BookingErrorMessage message) {
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
