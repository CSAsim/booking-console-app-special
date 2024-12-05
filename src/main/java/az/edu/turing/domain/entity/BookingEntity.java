package az.edu.turing.domain.entity;

import az.edu.turing.enums.StatusMessage;

import java.time.LocalDateTime;
import java.util.Objects;

public class BookingEntity {

    private Long id;
    private FlightEntity flight;
    private PassengerEntity passenger;
    private StatusMessage bookingStatus;
    private Double totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private BookingEntity() {
    }

    public BookingEntity(BookingEntityBuilder builder) {
        this.id = builder.id;
        this.flight = builder.flight;
        this.passenger = builder.passenger;
        this.bookingStatus = builder.bookingStatus;
        this.totalAmount = builder.totalAmount;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public BookingEntity(Long id, FlightEntity flight, PassengerEntity passenger, StatusMessage bookingState,
                         Double totalAmount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.flight = flight;
        this.passenger = passenger;
        this.bookingStatus = bookingState;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static class BookingEntityBuilder {
        private Long id;
        private FlightEntity flight;
        private PassengerEntity passenger;
        private StatusMessage bookingStatus;
        private Double totalAmount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static BookingEntityBuilder builder() {
            return new BookingEntityBuilder();
        }

        private BookingEntityBuilder() {}

        public BookingEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookingEntityBuilder flight(FlightEntity flight) {
            this.flight = flight;
            return this;
        }

        public BookingEntityBuilder passenger(PassengerEntity passenger) {
            this.passenger = passenger;
            return this;
        }

        public BookingEntityBuilder bookingStatus(StatusMessage bookingStatus) {
            this.bookingStatus = bookingStatus;
            return this;
        }

        public BookingEntityBuilder totalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public BookingEntityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BookingEntityBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public BookingEntity build() {
            return new BookingEntity(this);
        }
    }

    public Long getId() {
        return id;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public PassengerEntity getPassenger() {
        return passenger;
    }

    public StatusMessage getBookingStatus() {
        return bookingStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookingEntity that = (BookingEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(flight, that.flight) &&
                Objects.equals(passenger, that.passenger) && bookingStatus == that.bookingStatus &&
                Objects.equals(totalAmount, that.totalAmount) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, passenger, bookingStatus, totalAmount, createdAt);
    }

    @Override
    public String toString() {
        return String.format("BookingEntity{id=%d, flight=%s, passenger=%s, bookingState=%s, totalAmount=%s, " +
                        "createdAt=%s, updatedAt=%s}",
                id, flight, passenger, bookingStatus, totalAmount, createdAt, updatedAt);
    }
}
