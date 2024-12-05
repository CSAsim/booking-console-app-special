package az.edu.turing.mapper;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.model.dto.FlightDto;

public class FlightMapper implements EntityMapper<FlightDto, FlightEntity> {

    @Override
    public FlightDto toDto(FlightEntity flightEntity) {

        return FlightDto.FlightDtoBuilder.builder()
                .id(flightEntity.getId())
                .flightNumber(flightEntity.getFlightNumber())
                .departure(flightEntity.getDeparture())
                .destination(flightEntity.getDestination())
                .departureTime(flightEntity.getDepartureTime())
                .arrivalTime(flightEntity.getArrivalTime())
                .totalSeats(flightEntity.getTotalSeats())
                .availableSeats(flightEntity.getAvailableSeats())
                .flightStatus(flightEntity.getFlightStatus())
                .createdAt(flightEntity.getCreatedAt())
                .updatedAt(flightEntity.getUpdatedAt())
                .build();
    }
}
