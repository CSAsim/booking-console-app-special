package az.edu.turing.mapper;

import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.model.dto.BookingDto;

public class BookingMapper implements EntityMapper<BookingDto, BookingEntity>{

    private final FlightMapper flightMapper;
    private final PassengerMapper passengerMapper;

    public BookingMapper(FlightMapper flightMapper, PassengerMapper passengerMapper) {
        this.flightMapper = flightMapper;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public BookingDto toDto(BookingEntity bookingEntity) {

        return BookingDto.BookingDtoBuilder.builder()
                .id(bookingEntity.getId())
                .flight(flightMapper.toDto(bookingEntity.getFlight()))
                .passenger(passengerMapper.toDto(bookingEntity.getPassenger()))
                .totalAmount(bookingEntity.getTotalAmount())
                .createdAt(bookingEntity.getCreatedAt())
                .updatedAt(bookingEntity.getUpdatedAt())
                .bookingStatus(bookingEntity.getBookingStatus())
                .build();
    }
}
