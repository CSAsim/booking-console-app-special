package az.edu.turing.enums;

public enum BookingErrorMessage {
    NOT_FOUND(404, "There is not booking with this id"),
    ALREADY_EXIST(409, "Booking already exists with specified id");

    private final int status;
    private final String message;

    BookingErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
