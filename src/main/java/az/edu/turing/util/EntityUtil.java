package az.edu.turing.util;

import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.enums.StatusMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EntityUtil {
    private EntityUtil() {}

    public static FlightEntity getFlightInfo(ResultSet rs) throws SQLException {
        Long flightId = rs.getLong("id");
        String flightNumber = rs.getString("flight_number");
        String departure = rs.getString("departure");
        String destination = rs.getString("destination");
        LocalDateTime departureTime = rs.getTimestamp("departure_time").toLocalDateTime();
        LocalDateTime arrivalTime = rs.getTimestamp("arrival_time").toLocalDateTime();
        Integer totalSeats = rs.getInt("total_Seats");
        Integer availableSeats = rs.getInt("available_seats");
        String flightStatus = rs.getString("flight_status");
        LocalDateTime createdAtForFlight = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAtForFlight = rs.getTimestamp("updated_at").toLocalDateTime();

        return FlightEntity.FlightEntityBuilder.builder()
                .id(flightId)
                .flightNumber(flightNumber)
                .departure(departure)
                .destination(destination)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .totalSeats(totalSeats)
                .availableSeats(availableSeats)
                .flightStatus(StatusMessage.valueOf(flightStatus))
                .createdAt(createdAtForFlight)
                .updatedAt(updatedAtForFlight)
                .build();
    }

    public static BookingEntity getBookingInfo(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String bookingStatus = rs.getString("booking_status");
        Double totalAmount = rs.getDouble("total_amount");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();

        return BookingEntity.BookingEntityBuilder.builder()
                .id(id)
                .flight(EntityUtil.getFlightInfo(rs))
                .passenger(getPassengerInfo(rs))
                .bookingStatus(StatusMessage.valueOf(bookingStatus))
                .totalAmount(totalAmount)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    public static PassengerEntity getPassengerInfo(ResultSet rs) throws SQLException {
        Long id = rs.getLong("passenger_id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phone_number");
        boolean isDeleted = rs.getBoolean("deleted");
        LocalDateTime createdAt = rs.getTimestamp("p_created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("p_updated_at").toLocalDateTime();

        return PassengerEntity.PassengerEntityBuilder.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .email(email)
                .phoneNumber(phoneNumber)
                .isDeleted(isDeleted)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
