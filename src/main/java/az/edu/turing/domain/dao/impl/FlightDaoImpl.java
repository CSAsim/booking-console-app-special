package az.edu.turing.domain.dao.impl;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.enums.StatusMessage;
import az.edu.turing.util.EntityUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FlightDaoImpl extends FlightDao {

    private final ConnectionHelper connectionHelper;

    public FlightDaoImpl(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
        createTable();
    }

    public void createTable() {
        Connection collection = connectionHelper.getConnection();
        String createTableQuery = """
                CREATE TABLE IF NOT EXISTS flights
                (
                    id              BIGSERIAL PRIMARY KEY,
                    flight_number   VARCHAR(10) NOT NULL,
                    destination     VARCHAR(25) NOT NULL,
                    departure       VARCHAR(25) NOT NULL DEFAULT 'KIEV',
                    departure_time  TIMESTAMP   NOT NULL,
                    arrival_time    TIMESTAMP   NOT NULL,
                    total_seats     INT         NOT NULL,
                    available_seats INT         NOT NULL,
                    flight_status   VARCHAR(10) NOT NULL DEFAULT 'PENDING',
                    created_at      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    updated_at      TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
                );
                """;
        try {
            Statement statement = collection.createStatement();
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FlightEntity create(FlightEntity flightEntity) {
        Connection connection = connectionHelper.getConnection();
        String insertQuery = """
                INSERT INTO flights (flight_number, departure, destination, departure_time, arrival_time, total_seats,
                available_seats)
                VALUES (?,?,?,?,?,?,?);
                """;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, flightEntity.getFlightNumber());
            preparedStatement.setString(2, flightEntity.getDeparture());
            preparedStatement.setString(2, flightEntity.getDestination());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(flightEntity.getDepartureTime()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(flightEntity.getArrivalTime()));
            preparedStatement.setInt(5, flightEntity.getTotalSeats());
            preparedStatement.setInt(6, flightEntity.getAvailableSeats());
            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return getByFlightNumber(flightEntity.getFlightNumber());
    }

    @Override
    public Collection<FlightEntity> getAll() {
        Connection connection = connectionHelper.getConnection();
        String querySelect = """
                    SELECT f.id AS flight_id,
                    f.created_at AS f_created_at,
                    f.updated_at AS f_updated_at,
                    f.*
                    FROM flights f;
                    """;
        List<FlightEntity> flightEntities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            statement.execute(querySelect);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                flightEntities.add(EntityUtil.getFlightInfo(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flightEntities;
    }

    @Override
    public Collection<FlightEntity> getBySpecially(LocalDateTime departureTime, String destination) {
        List<FlightEntity> flightEntities = new ArrayList<>();
        String querySelect = """
                SELECT f.id AS flight_id,
                f.created_at AS f_created_at,
                f.updated_at AS f_updated_at,
                f.* FROM flights
                WHERE flight_status = 'PENDING'
                """;
        List<Object> parameters = new ArrayList<>();

        if (departureTime != null) {
            querySelect += " AND departure_time >= ?";
            parameters.add(Timestamp.valueOf(departureTime));
        }

        if (destination != null && !destination.trim().isEmpty()) {
            querySelect += " AND destination = ?";
            parameters.add(destination.trim());
        }

        querySelect += " LIMIT 5";

        try (Connection connection = connectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querySelect)) {

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    flightEntities.add(EntityUtil.getFlightInfo(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch flights with the specified criteria", e);
        }
        return flightEntities;
    }

    @Override
    public Optional<FlightEntity> getById(Long id) {
        Connection connection = connectionHelper.getConnection();
        List<FlightEntity> flight = new ArrayList<>();
        String querySelectById = """
                SELECT f.id AS flight_id,
                f.created_at AS f_created_at,
                f.updated_at AS f_updated_at,
                f.* FROM flights f WHERE f.id = ?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querySelectById);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                flight.add(EntityUtil.getFlightInfo(resultSet));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flight.stream().findFirst();
    }

    @Override
    public FlightEntity deleteById(Long id) {
        Connection connection = connectionHelper.getConnection();
        String query = """
                UPDATE flights SET flight_status = ?, updated_at = ? WHERE id = ?;
                """;
        String queryUpdateBooking = """
                UPDATE bookings SET booking_status = ?, updated_at = ? WHERE bookings.flight_id = ?;
                """;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, StatusMessage.CANCELLED.toString());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            preparedStatement = connection.prepareStatement(queryUpdateBooking);
            preparedStatement.setString(1, StatusMessage.CANCELLED.toString());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            connection.close();
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
    public FlightEntity update(Long id, FlightEntity flightEntity) {
        Connection connection = connectionHelper.getConnection();

        String queryUpdate = """
                UPDATE flights SET flight_number = ?,
                destination = ?,
                departure_time = ?,
                arrival_time = ?,
                total_seats = ?,
                available_seats = ?,
                flight_status = ?,
                updated_at = ?
                WHERE id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, flightEntity.getDestination());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(flightEntity.getDepartureTime()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(flightEntity.getArrivalTime()));
            preparedStatement.setInt(4, flightEntity.getTotalSeats());
            preparedStatement.setInt(5, flightEntity.getAvailableSeats());
            preparedStatement.setString(6, flightEntity.getFlightStatus().toString());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(8, id);
            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Failed to update flight with ID: " + flightEntity.getId(), e);
        }
        return getById(flightEntity.getId()).get();
    }

    @Override
    public boolean existsById(Long id) {
        Connection connection = connectionHelper.getConnection();
        String query = """
                SELECT f.id AS flight_id,
                f.created_at AS f_created_at,
                f.updated_at AS f_updated_at,
                f.* FROM flights f WHERE f.id = ?;""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean existsByFlightNumber(String flightNumber) {
        Connection connection = connectionHelper.getConnection();
        String query = """
                SELECT f.id AS flight_id,
                f.created_at AS f_created_at,
                f.updated_at AS f_updated_at,
                f.* FROM flights f WHERE f.flight_number = ?;""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, flightNumber);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public FlightEntity getByFlightNumber(String flightNumber) {
        Connection connection = connectionHelper.getConnection();
        String query = """
                SELECT f.id AS flight_id,
                f.created_at AS f_created_at,
                f.updated_at AS f_updated_at,
                f.* FROM flights f WHERE flight_number = ?;""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, flightNumber);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            return resultSet.next() ? EntityUtil.getFlightInfo(resultSet) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}