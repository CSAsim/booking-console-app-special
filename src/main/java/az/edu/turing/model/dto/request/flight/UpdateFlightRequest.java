package az.edu.turing.model.dto.request.flight;

import az.edu.turing.enums.StatusMessage;

import java.time.LocalDateTime;

public class UpdateFlightRequest {

    private long id;
    private String flightNumber;
    private String departure;
    private String destination;
    private LocalDateTime arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private StatusMessage flightStatus;
    private LocalDateTime updatedAt;

    public UpdateFlightRequest(UpdateFlightRequestBuilder builder) {
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.departure = builder.departure;
        this.destination = builder.destination;
        this.arrivalTime = builder.arrivalTime;
        this.totalSeats = builder.totalSeats;
        this.availableSeats = builder.availableSeats;
        this.flightStatus = builder.flightStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public static class UpdateFlightRequestBuilder {

        private long id;
        private String flightNumber;
        private String departure;
        private String destination;
        private LocalDateTime arrivalTime;
        private int totalSeats;
        private StatusMessage flightStatus;
        private int availableSeats;

        public static UpdateFlightRequestBuilder builder() {
            return new UpdateFlightRequestBuilder();
        }

        public UpdateFlightRequestBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UpdateFlightRequestBuilder flightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public UpdateFlightRequestBuilder departure(String departure) {
            this.departure = departure;
            return this;
        }

        public UpdateFlightRequestBuilder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public UpdateFlightRequestBuilder arrivalTime(LocalDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public UpdateFlightRequestBuilder totalSeats(int totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        public UpdateFlightRequestBuilder availableSeats(int availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        public UpdateFlightRequestBuilder flightStatus(StatusMessage flightStatus) {
            this.flightStatus = flightStatus;
            return this;
        }

        public UpdateFlightRequest build() {
            return new UpdateFlightRequest(this);
        }
    }

    public long getId() {
        return id;
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

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public StatusMessage getFlightStatus() {
        return flightStatus;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
