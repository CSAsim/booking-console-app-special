package az.edu.turing.domain.entity;

import az.edu.turing.enums.StatusMessage;

import java.time.LocalDateTime;

public class FlightEntity {

    private Long id;
    private String flightNumber;
    private String departure;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer totalSeats;
    private Integer availableSeats;
    private StatusMessage flightStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private FlightEntity() {
    }

    public FlightEntity(FlightEntityBuilder builder) {
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

    public static class FlightEntityBuilder {
        private Long id;
        private String flightNumber;
        private String departure;
        private String destination;
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
        private Integer totalSeats;
        private Integer availableSeats;
        private StatusMessage flightStatus;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static FlightEntityBuilder builder() {
            return new FlightEntityBuilder();
        }

        public FlightEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FlightEntityBuilder flightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public FlightEntityBuilder departure(String departure) {
            this.departure = departure;
            return this;
        }

        public FlightEntityBuilder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public FlightEntityBuilder departureTime(LocalDateTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public FlightEntityBuilder arrivalTime(LocalDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public FlightEntityBuilder totalSeats(Integer totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        public FlightEntityBuilder availableSeats(Integer availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        public FlightEntityBuilder flightStatus(StatusMessage flightStatus) {
            this.flightStatus = flightStatus;
            return this;
        }

        public FlightEntityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FlightEntityBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FlightEntity build() {
            return new FlightEntity(this);
        }
    }

    public Long getId() {
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

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public StatusMessage getFlightStatus() {
        return flightStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FlightEntity that = (FlightEntity) obj;
        return flightNumber.equals(that.flightNumber) &&
                departureTime.equals(that.departureTime);
    }

    @Override
    public int hashCode() {
        int result = flightNumber.hashCode();
        result = 31 * result + departureTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("FlightEntity{id=%d, flightNumber=%s, departure=%s, destination=%s, departureTime=%s, arrivalTime=%s, totalSeats=%d, availableSeats=%d, flightStatus=%s,createdAt=%s, updatedAt=%s}",
                id, flightNumber, departure, destination, departureTime, arrivalTime, totalSeats, availableSeats, flightStatus, createdAt, updatedAt);
    }
}
