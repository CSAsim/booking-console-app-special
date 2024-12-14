package az.edu.turing.enums;

public enum PassengerErrorMessage {

    NOT_FOUND(404, "There is not passenger with this id"),
    ALREADY_EXIST(409, "Passenger already exists with specified id"),
    NOT_FOUND_WITH_EMAIL(404, "There is not passenger with this email"),
    ALREADY_EXIST_WITH_EMAIL(409, "Passenger already exists with specified email");

    private final int status;
    private final String message;

    PassengerErrorMessage(int status, String message) {
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
