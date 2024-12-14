package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.enums.FLightErrorMessage;
import az.edu.turing.exception.flight.FlightAlreadyExistsException;
import az.edu.turing.exception.flight.FlightNotFoundException;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.FlightDto;
import az.edu.turing.model.dto.request.flight.CreateFlightRequest;
import az.edu.turing.model.dto.request.flight.UpdateFlightRequest;
import az.edu.turing.service.inter.FlightService;

import java.util.ArrayList;
import java.util.List;

public class FlightServiceImpl implements FlightService {
    private final FlightDao flightDao;
    private final FlightMapper mapper;

    public FlightServiceImpl(FlightDao flightDao, FlightMapper mapper) {
        this.flightDao = flightDao;
        this.mapper = mapper;
    }

    @Override
    public FlightDto createFlight(CreateFlightRequest request) {
        if(flightDao.existsByFlightNumber(request.getFlightNumber())) {
            throw new FlightAlreadyExistsException(FLightErrorMessage.ALREADY_EXIST_WITH_FLIGHT_NUMBER);
        }
        FlightEntity flight = FlightEntity.FlightEntityBuilder.builder()
                .flightNumber(request.getFlightNumber())
                .departure(request.getDeparture())
                .destination(request.getDestination())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getAvailableSeats())
                .updatedAt(request.getUpdatedAt())
                .createdAt(request.getCreatedAt())
                .build();
        return mapper.toDto(flightDao.create(flight));
    }

    @Override
    public FlightDto getFlight(long id) {
        if(!flightDao.existsById(id)) {
            throw new FlightNotFoundException(FLightErrorMessage.NOT_FOUND);
        }
        FlightEntity flight = flightDao.getById(id).get();
        return mapper.toDto(flight);
    }

    @Override
    public FlightDto updateFlight(long id, UpdateFlightRequest request) {
        if(!flightDao.existsByFlightNumber(request.getFlightNumber())) {
            throw new FlightAlreadyExistsException(FLightErrorMessage.ALREADY_EXIST_WITH_FLIGHT_NUMBER);
        }
        FlightEntity flight = flightDao.getById(id).orElseThrow(()-> new FlightNotFoundException(FLightErrorMessage.NOT_FOUND));
        return mapper.toDto(flight);
    }

    @Override
    public void deleteFlight(long id) {
        if(!flightDao.existsById(id)) {
            throw new FlightNotFoundException(FLightErrorMessage.NOT_FOUND);
        }
        flightDao.deleteById(id);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        List<FlightEntity> flightEntities = flightDao.getAll().stream().toList();
        List<FlightDto> flightDto = new ArrayList<>();
        for (FlightEntity flight : flightEntities) {
            flightDto.add(mapper.toDto(flight));
        }
        return flightDto;
    }
}
