package az.edu.turing.model.dto.request.booking;

import az.edu.turing.model.dto.request.passenger.CreatePassengerRequest;

import java.time.LocalDateTime;
import java.util.List;

public class CreateBookingRequest {

    private final String flightNumber;
    private final List<CreatePassengerRequest> passengers;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CreateBookingRequest(CreateBookingRequestBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.passengers = builder.passengers;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static class CreateBookingRequestBuilder {
        private String flightNumber;
        private List<CreatePassengerRequest> passengers;

        public static CreateBookingRequestBuilder builder(){
            return new CreateBookingRequestBuilder();
        }

        public CreateBookingRequestBuilder flight(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public CreateBookingRequestBuilder passengers(List<CreatePassengerRequest> passengers) {
            this.passengers = passengers;
            return this;
        }

        public CreateBookingRequest build() {
            return new CreateBookingRequest(this);
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public List<CreatePassengerRequest> getPassengers() {
        return passengers;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
