package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.PassengerDto;
import az.edu.turing.model.dto.request.passenger.CreatePassengerRequest;
import az.edu.turing.model.dto.request.passenger.UpdatePassengerRequest;
import az.edu.turing.service.inter.PassengerService;

import java.util.List;

public class PassengerServiceImpl implements PassengerService {
    private final PassengerDao passengerDao;
    private final PassengerMapper mapper;

    public PassengerServiceImpl(PassengerDao passengerDao, PassengerMapper mapper) {
        this.passengerDao = passengerDao;
        this.mapper = mapper;
    }

    @Override
    public PassengerDto createPassenger(CreatePassengerRequest request) {
        return null;
    }

    @Override
    public PassengerDto getPassenger(long id) {
        return null;
    }

    @Override
    public PassengerDto updatePassenger(long id, UpdatePassengerRequest request) {
        return null;
    }

    @Override
    public void deletePassenger(long id) {

    }

    @Override
    public List<PassengerDto> getAllPassengers() {
        return List.of();
    }
}
