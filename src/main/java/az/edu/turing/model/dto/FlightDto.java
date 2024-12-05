package az.edu.turing.model.dto;

import az.edu.turing.enums.StatusMessage;

import java.time.LocalDateTime;

public class FlightDto {

    private long id;
    private String flightNumber;
    private String departure;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private StatusMessage flightStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FlightDto(FlightDtoBuilder builder) {
        this.id = builder.id;
        this.flightNumber = builder.flightNumber;
        this.departure = builder.departure;
        this.destination = builder.destination;
        this.departureTime = builder.departureTime;
        this.arrivalTime = builder.arrivalTime;
        this.totalSeats = builder.totalSeats;
        this.availableSeats = builder.availableSeats;
        this.flightStatus = builder.flightStatus;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class FlightDtoBuilder {

        private long id;
        private String flightNumber;
        private String departure;
        private String destination;
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
        private int totalSeats;
        private int availableSeats;
        private StatusMessage flightStatus;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static FlightDtoBuilder builder() {
            return new FlightDtoBuilder();
        }

        public FlightDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public FlightDtoBuilder flightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public FlightDtoBuilder departure(String departure) {
            this.departure = departure;
            return this;
        }

        public FlightDtoBuilder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public FlightDtoBuilder departureTime(LocalDateTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public FlightDtoBuilder arrivalTime(LocalDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public FlightDtoBuilder totalSeats(int totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        public FlightDtoBuilder availableSeats(int availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        public FlightDtoBuilder flightStatus(StatusMessage flightStatus) {
            this.flightStatus = flightStatus;
            return this;
        }

        public FlightDtoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FlightDtoBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FlightDto build() {
            return new FlightDto(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public StatusMessage getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(StatusMessage flightStatus) {
        this.flightStatus = flightStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return String.format(
                "FlightDto {flightNumber=%s, departure=%s, destination=%s, departureTime=%s, arrivalTime=%s, availableSeats=%d, flightStatus=%s, createdAt=%s, updatedAt=%s}",
                flightNumber,
                departure,
                destination,
                departureTime,
                arrivalTime,
                availableSeats,
                flightStatus,
                createdAt,
                updatedAt
        );
    }

}
