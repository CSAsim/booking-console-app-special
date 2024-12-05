package az.edu.turing.model.dto.request.booking;

import az.edu.turing.enums.StatusMessage;
import az.edu.turing.model.dto.request.passenger.UpdatePassengerRequest;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateBookingRequest {

    private final long id;
    private final String flightNumber;
    private final List<UpdatePassengerRequest> passengers;
    private final StatusMessage bookingStatus;
    private final LocalDateTime updatedAt;

    public UpdateBookingRequest(UpdateBookingRequestBuilder builder) {
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.passengers = builder.passengers;
        this.bookingStatus = builder.bookingStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public static class UpdateBookingRequestBuilder {

        private long id;
        private String flightNumber;
        private List<UpdatePassengerRequest> passengers;
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

        public UpdateBookingRequestBuilder passengers(List<UpdatePassengerRequest> passengers) {
            this.passengers = passengers;
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

    public List<UpdatePassengerRequest> getPassengers() {
        return passengers;
    }

    public StatusMessage getBookingStatus() {
        return bookingStatus;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
