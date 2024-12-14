package az.edu.turing.model.dto.request.booking;

import java.time.LocalDateTime;

public class CreateBookingRequest {

    private final long flightId;
    private final long passengerId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CreateBookingRequest(CreateBookingRequestBuilder builder) {
        this.flightId = builder.flightId;
        this.passengerId = builder.passengerId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static class CreateBookingRequestBuilder {
        private long flightId;
        private long passengerId;;

        public static CreateBookingRequestBuilder builder(){
            return new CreateBookingRequestBuilder();
        }

        public CreateBookingRequestBuilder flightId(long flightId) {
            this.flightId = flightId;
            return this;
        }

        public CreateBookingRequestBuilder passengers(long passengerId) {
            this.passengerId = passengerId;
            return this;
        }

        public CreateBookingRequest build() {
            return new CreateBookingRequest(this);
        }
    }

    public long getFlightId() {
        return flightId;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
