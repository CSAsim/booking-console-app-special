package az.edu.turing.service.inter;

import az.edu.turing.model.dto.PassengerDto;
import az.edu.turing.model.dto.request.passenger.CreatePassengerRequest;
import az.edu.turing.model.dto.request.passenger.UpdatePassengerRequest;

import java.util.List;

public interface PassengerService {

    PassengerDto createPassenger(CreatePassengerRequest request);
    List<PassengerDto> getAll();
    PassengerDto getPassenger(long id);
    PassengerDto updatePassenger(long id, UpdatePassengerRequest request);
    void deletePassenger(long id);
    List<PassengerDto> getAllByFlightId(long flightId);
}
