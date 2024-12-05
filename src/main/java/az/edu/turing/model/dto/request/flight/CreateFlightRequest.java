package az.edu.turing.model.dto.request.flight;

import java.time.LocalDateTime;

public class CreateFlightRequest {

    private String flightNumber;
    private String departure;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CreateFlightRequest(CreateFlightRequestBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.departure = builder.departure;
        this.destination = builder.destination;
        this.departureTime = builder.departureTime;
        this.arrivalTime = builder.arrivalTime;
        this.totalSeats = builder.totalSeats;
        this.availableSeats = builder.availableSeats;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static class CreateFlightRequestBuilder {

        private String flightNumber;
        private String departure;
        private String destination;
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
        private int totalSeats;
        private int availableSeats;

        public static CreateFlightRequestBuilder builder() {
            return new CreateFlightRequestBuilder();
        }

        public CreateFlightRequestBuilder flightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public CreateFlightRequestBuilder departure(String departure) {
            this.departure = departure;
            return this;
        }

        public CreateFlightRequestBuilder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public CreateFlightRequestBuilder departureTime(LocalDateTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public CreateFlightRequestBuilder arrivalTime(LocalDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public CreateFlightRequestBuilder totalSeats(int totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        public CreateFlightRequestBuilder availableSeats(int availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        public CreateFlightRequest build() {
            return new CreateFlightRequest(this);
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
