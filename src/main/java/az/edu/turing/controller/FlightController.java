package az.edu.turing.controller;

import az.edu.turing.model.dto.FlightDto;
import az.edu.turing.model.dto.request.flight.CreateFlightRequest;
import az.edu.turing.model.dto.request.flight.UpdateFlightRequest;
import az.edu.turing.service.inter.FlightService;

import java.util.List;

public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public FlightDto create(CreateFlightRequest request) {
        return flightService.createFlight(request);
    }

    public List<FlightDto> getAll() {
        return flightService.getAllFlights();
    }

    public FlightDto getById(long id) {
        return flightService.getFlight(id);
    }

    public FlightDto update(long id, UpdateFlightRequest request) {
        return flightService.updateFlight(id, request);
    }

    public void delete(long id) {
        flightService.deleteFlight(id);
    }
}
