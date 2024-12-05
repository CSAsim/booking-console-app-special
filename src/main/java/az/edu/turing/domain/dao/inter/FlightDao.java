package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.FlightEntity;

import java.time.LocalDateTime;
import java.util.Collection;

public abstract class FlightDao implements Dao <FlightEntity, Long> {
    public abstract FlightEntity getByFlightNumber(String flightNumber);
    public abstract Collection<FlightEntity> getBySpecially(LocalDateTime departureTime, String destination);
    public abstract boolean existsByFlightNumber(String flightNumber);
}
