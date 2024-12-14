package az.edu.turing.enums;

public enum FLightErrorMessage {

    NOT_FOUND(404, "There is not flight with this id"),
    ALREADY_EXIST(409, "Flight already exists with specified id"),
    NOT_FOUND_WITH_FLIGHT_NUMBER(404, "There is not flight with this flight number"),
    ALREADY_EXIST_WITH_FLIGHT_NUMBER(409, "Flight already exists with specified id");

    private final int status;
    private final String message;

    FLightErrorMessage(int status, String message) {
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
