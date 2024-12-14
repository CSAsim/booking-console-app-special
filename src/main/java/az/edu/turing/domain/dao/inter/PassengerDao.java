package az.edu.turing.domain.dao.inter;

import az.edu.turing.domain.dao.Dao;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.Collection;

public abstract class PassengerDao implements Dao<PassengerEntity, Long> {
    public abstract PassengerEntity getByEmail(String email);
    public abstract boolean existsByEmail(String email);
    public abstract Collection<PassengerEntity> getAllByFlightId(Long flightId);
}
