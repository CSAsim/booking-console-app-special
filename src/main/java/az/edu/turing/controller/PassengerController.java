package az.edu.turing.controller;

import az.edu.turing.model.dto.PassengerDto;
import az.edu.turing.model.dto.request.passenger.CreatePassengerRequest;
import az.edu.turing.model.dto.request.passenger.UpdatePassengerRequest;
import az.edu.turing.service.inter.PassengerService;

import java.util.List;

public class PassengerController {

    private final PassengerService passengerService;


    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public PassengerDto create(CreatePassengerRequest request) {
        return passengerService.createPassenger(request);
    }

    public List<PassengerDto> getAll() {
        return passengerService.getAllPassengers();
    }

    public PassengerDto getById(long id) {
        return passengerService.getPassenger(id);
    }

    public PassengerDto update(long id, UpdatePassengerRequest request) {
        return passengerService.updatePassenger(id, request);
    }

    public void delete(long id) {
        passengerService.deletePassenger(id);
    }
}
