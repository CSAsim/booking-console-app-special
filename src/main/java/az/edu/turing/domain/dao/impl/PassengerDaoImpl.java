package az.edu.turing.domain.dao.impl;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.enums.StatusMessage;
import az.edu.turing.util.EntityUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PassengerDaoImpl extends PassengerDao {

    private final ConnectionHelper connectionHelper;

    public PassengerDaoImpl(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
        createTable();
    }

    public void createTable() {
        Connection connection = connectionHelper.getConnection();
        String createTableQuery = """
                CREATE TABLE IF NOT EXISTS passengers
                (
                    id           BIGSERIAL PRIMARY KEY,
                    name         VARCHAR(25) NOT NULL,
                    surname      VARCHAR(35) NOT NULL,
                    email        VARCHAR(50) NOT NULL UNIQUE CHECK (email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'),
                    phone_number VARCHAR(20) NOT NULL CHECK (phone_number ~ '^\\+?[0-9\\s\\-()]+$'),
                    deleted      BOOLEAN     NOT NULL DEFAULT false,
                    created_at   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_at   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
                );
                """;
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PassengerEntity create(PassengerEntity passengerEntity) {
        Connection connection = connectionHelper.getConnection();
        String queryInsert = """
                INSERT INTO passengers (name, surname, email, phone_number)
                VALUES (?, ?, ?, ?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryInsert)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, passengerEntity.getName());
            preparedStatement.setString(2, passengerEntity.getSurname());
            preparedStatement.setString(3, passengerEntity.getEmail());
            preparedStatement.setString(4, passengerEntity.getPhoneNumber());
            preparedStatement.executeBatch();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return getByEmail(passengerEntity.getEmail());
    }

    @Override
    public Collection<PassengerEntity> getAll() {
        Connection connection = connectionHelper.getConnection();
        String querySelect = """
                SELECT p.id AS passenger_id,
                p.created_at AS p_created_at,
                p.updated_at AS p_updated_at,
                p.* FROM passengers p;
                """;
        List<PassengerEntity> passengers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            statement.execute(querySelect);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                passengers.add(EntityUtil.getPassengerInfo(resultSet));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengers;
    }

    @Override
    public Optional<PassengerEntity> getById(Long id) {
        Connection connection = connectionHelper.getConnection();
        String querySelect = """
                SELECT p.id AS passenger_id, p.created_at AS p_created_at,
                p.updated_at AS p_updated_at,
                p.* FROM passengers p WHERE p.id = ?;
                """;
        List<PassengerEntity> passenger = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(querySelect)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if(resultSet.next()) {
                passenger.add(EntityUtil.getPassengerInfo(resultSet));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(passenger.getFirst());
    }

    @Override
    public PassengerEntity deleteById(Long id) {
        Connection connection = connectionHelper.getConnection();
        String queryDelete = """
                UPDATE passengers SET deleted = false WHERE id = ?;
                """;
        String queryUpdate = """
                UPDATE bookings SET booking_status = ? WHERE passenger_id = ?
                """;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(queryDelete);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setString(1, StatusMessage.CANCELLED.toString());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return getById(id).get();
    }

    @Override
    public PassengerEntity update(Long id, PassengerEntity passengerEntity) {
        Connection connection = connectionHelper.getConnection();
        String queryUpdate = """
                UPDATE passengers SET name = ?, surname = ?, email = ?, phone_number = ?, updated_at = ?
                WHERE id = ?;
                """;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setString(1, passengerEntity.getName());
            preparedStatement.setString(2, passengerEntity.getSurname());
            preparedStatement.setString(3, passengerEntity.getEmail());
            preparedStatement.setString(4, passengerEntity.getPhoneNumber());
            preparedStatement.setTimestamp(5,Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return getById(passengerEntity.getId()).get();
    }

    @Override
    public boolean existsById(Long id) {
        Connection connection = connectionHelper.getConnection();
        String querySelect = """
                SELECT p.id AS passenger_id, p.created_at AS p_created_at,
                p.updated_at AS p_updated_at,
                p.* FROM passengers p WHERE p.id = ?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            preparedStatement.close();
            connection.close();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PassengerEntity getByEmail(String email) {
        Connection connection = connectionHelper.getConnection();
        String querySelect = """
                SELECT p.id AS passenger_id, p.created_at AS p_created_at,
                p.updated_at AS p_updated_at,
                p.* FROM passengers p WHERE p.email = ?;
                """;
        PassengerEntity passengerEntity = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                passengerEntity = EntityUtil.getPassengerInfo(resultSet);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengerEntity;
    }

    @Override
    public boolean existsByEmail(String email) {
        Connection connection = connectionHelper.getConnection();
        String querySelect = """
                SELECT p.id AS passenger_id, p.created_at AS p_created_at,
                p.updated_at AS p_updated_at,
                p.* FROM passengers p WHERE p.email = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(querySelect)){
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            connection.close();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<PassengerEntity> getAllByFlightId(Long flightId) {
        Connection connection = connectionHelper.getConnection();
        String selectQuery = """
                SELECT p.id         as passenger_id,
                       p.updated_at as p_updated_at,
                       p.created_at as p_created_at,
                       p.*
                FROM passengers p
                         INNER JOIN public.bookings b on p.id = b.passenger_id
                    AND b.flight_id = ?;
                """;
        List<PassengerEntity> passengers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, flightId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                passengers.add(EntityUtil.getPassengerInfo(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengers;
    }
}
