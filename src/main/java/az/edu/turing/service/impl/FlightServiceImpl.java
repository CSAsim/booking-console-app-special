package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.FlightDto;
import az.edu.turing.model.dto.request.flight.CreateFlightRequest;
import az.edu.turing.model.dto.request.flight.UpdateFlightRequest;
import az.edu.turing.service.inter.FlightService;

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
        return null;
    }

    @Override
    public FlightDto getFlight(long id) {
        return null;
    }

    @Override
    public FlightDto updateFlight(long id, UpdateFlightRequest request) {
        return null;
    }

    @Override
    public void deleteFlight(long id) {

    }

    @Override
    public List<FlightDto> getAllFlights() {
        return List.of();
    }
}
