package az.edu.turing.model.dto.request.booking;

import az.edu.turing.enums.StatusMessage;

import java.time.LocalDateTime;

public class UpdateBookingRequest {

    private final long id;
    private final String flightNumber;
    private final long passengerId;
    private final StatusMessage bookingStatus;
    private final LocalDateTime updatedAt;

    public UpdateBookingRequest(UpdateBookingRequestBuilder builder) {
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.passengerId = builder.passengerId;
        this.bookingStatus = builder.bookingStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public static class UpdateBookingRequestBuilder {

        private long id;
        private String flightNumber;
        private long passengerId;
        private StatusMessage bookingStatus;

        public static UpdateBookingRequestBuilder builder() {
            return new UpdateBookingRequestBuilder();
        }

        public UpdateBookingRequestBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UpdateBookingRequestBuilder flight(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public UpdateBookingRequestBuilder passengers(long passengerId) {
            this.passengerId = passengerId;
            return this;
        }

        public UpdateBookingRequestBuilder bookingStatus(StatusMessage bookingStatus) {
            this.bookingStatus = bookingStatus;
            return this;
        }

        public UpdateBookingRequest build() {
            return new UpdateBookingRequest(this);
        }
    }

    public long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public StatusMessage getBookingStatus() {
        return bookingStatus;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
