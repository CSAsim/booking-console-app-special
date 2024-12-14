package az.edu.turing.model.dto;

import az.edu.turing.enums.StatusMessage;

import java.time.LocalDateTime;

public class BookingDto {

    private long id;
    private FlightDto flight;
    private PassengerDto passenger;
    private StatusMessage bookingStatus;
    private double totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BookingDto(BookingDtoBuilder builder) {
        this.id = builder.id;
        this.flight = builder.flight;
        this.passenger = builder.passenger;
        this.bookingStatus = builder.bookingStatus;
        this.totalAmount = builder.totalAmount;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class BookingDtoBuilder {
        private long id;
        private FlightDto flight;
        private PassengerDto passenger;
        private StatusMessage bookingStatus;
        private Double totalAmount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static BookingDtoBuilder builder() {
            return new BookingDtoBuilder();
        }

        public BookingDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public BookingDtoBuilder flight(FlightDto flight) {
            this.flight = flight;
            return this;
        }

        public BookingDtoBuilder passenger(PassengerDto passenger) {
            this.passenger = passenger;
            return this;
        }

        public BookingDtoBuilder bookingStatus(StatusMessage bookingStatus) {
            this.bookingStatus = bookingStatus;
            return this;
        }

        public BookingDtoBuilder totalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public BookingDtoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BookingDtoBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public BookingDto build() {
            return new BookingDto(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FlightDto getFlight() {
        return flight;
    }

    public void setFlight(FlightDto flight) {
        this.flight = flight;
    }

    public PassengerDto getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerDto passenger) {
        this.passenger = passenger;
    }

    public StatusMessage getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(StatusMessage bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
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
                "BookingDto {updatedAt=%s, createdAt=%s, totalAmount=%.2f, bookingStatus=%s, passenger=%s, flight=%s}",
                updatedAt,
                createdAt,
                totalAmount,
                bookingStatus,
                passenger,
                flight
        );
    }

}
