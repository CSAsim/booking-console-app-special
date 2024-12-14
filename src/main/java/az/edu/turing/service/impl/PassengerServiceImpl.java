package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.enums.FLightErrorMessage;
import az.edu.turing.enums.PassengerErrorMessage;
import az.edu.turing.exception.flight.FlightNotFoundException;
import az.edu.turing.exception.passenger.PassengerAlreadyExistsException;
import az.edu.turing.exception.passenger.PassengerNotFoundException;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.PassengerDto;
import az.edu.turing.model.dto.request.passenger.CreatePassengerRequest;
import az.edu.turing.model.dto.request.passenger.UpdatePassengerRequest;
import az.edu.turing.service.inter.PassengerService;

import java.util.ArrayList;
import java.util.List;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerDao passengerDao;
    private final PassengerMapper mapper;
    private final FlightDao flightDao;

    public PassengerServiceImpl(PassengerDao passengerDao, PassengerMapper mapper, FlightDao flightDao) {
        this.passengerDao = passengerDao;
        this.mapper = mapper;
        this.flightDao = flightDao;
    }

    @Override
    public PassengerDto createPassenger(CreatePassengerRequest request) {
        if(passengerDao.existsByEmail(request.getEmail())) {
            throw new PassengerAlreadyExistsException(PassengerErrorMessage.ALREADY_EXIST_WITH_EMAIL);
        }
        PassengerEntity passenger = PassengerEntity.PassengerEntityBuilder.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .build();
        return mapper.toDto(passengerDao.create(passenger));
    }

    @Override
    public List<PassengerDto> getAll() {
        List<PassengerEntity> passengerEntities = passengerDao.getAll().stream().toList();
        List<PassengerDto> passengerDto = new ArrayList<>();
        for (PassengerEntity passenger : passengerEntities) {
            passengerDto.add(mapper.toDto(passenger));
        }
        return passengerDto;
    }

    @Override
    public PassengerDto getPassenger(long id) {
        PassengerEntity passenger = passengerDao.getById(id)
                .orElseThrow(() -> new PassengerNotFoundException(PassengerErrorMessage.NOT_FOUND));
        return mapper.toDto(passenger);
    }

    @Override
    public PassengerDto updatePassenger(long id, UpdatePassengerRequest request) {
        if(!passengerDao.existsByEmail(request.getEmail())) {
            throw new PassengerNotFoundException(PassengerErrorMessage.NOT_FOUND_WITH_EMAIL);
        }
        PassengerEntity passenger = passengerDao.getById(id)
                .orElseThrow(() -> new PassengerNotFoundException(PassengerErrorMessage.NOT_FOUND));
        return mapper.toDto(passengerDao.update(id, passenger));
    }

    @Override
    public void deletePassenger(long id) {
        if(!passengerDao.existsById(id)) {
            throw new PassengerNotFoundException(PassengerErrorMessage.NOT_FOUND);
        }
        passengerDao.deleteById(id);
    }

    @Override
    public List<PassengerDto> getAllByFlightId(long flightId) {
        if(!flightDao.existsById(flightId)) {
            throw new FlightNotFoundException(FLightErrorMessage.NOT_FOUND);
        }
        List<PassengerEntity> passengerEntities = passengerDao.getAllByFlightId(flightId).stream().toList();
        List<PassengerDto> passengerDto = new ArrayList<>();
        for (PassengerEntity passenger : passengerEntities) {
            passengerDto.add(mapper.toDto(passenger));
        }
        return passengerDto;
    }
}
