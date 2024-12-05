package az.edu.turing.service.inter;

import az.edu.turing.model.dto.FlightDto;
import az.edu.turing.model.dto.request.flight.CreateFlightRequest;
import az.edu.turing.model.dto.request.flight.UpdateFlightRequest;

import java.util.List;

public interface FlightService {

    FlightDto createFlight(CreateFlightRequest request);
    FlightDto getFlight(long id);
    FlightDto updateFlight(long id, UpdateFlightRequest request);
    void deleteFlight(long id);
    List<FlightDto> getAllFlights();
}
