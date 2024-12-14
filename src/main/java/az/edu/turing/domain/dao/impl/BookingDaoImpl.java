package az.edu.turing.domain.dao.impl;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.enums.StatusMessage;
import az.edu.turing.util.EntityUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class BookingDaoImpl extends BookingDao {


    private final ConnectionHelper connectionHelper;

    public BookingDaoImpl(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
        createTable();
    }

    private void createTable() {
        Connection connection = connectionHelper.getConnection();
        String createTableSql = """
                CREATE TABLE IF NOT EXISTS bookings
                  (
                      id             BIGSERIAL primary key,
                      flight_id      BIGINT           NOT NULL,
                      passenger_id   BIGINT           NOT NULL,
                      booking_status VARCHAR          NOT NULL DEFAULT 'CONFIRMED',
                      total_amount   DOUBLE PRECISION NOT NULL,
                      created_at     TIMESTAMPTZ      NOT NULL default CURRENT_TIMESTAMP,
                      updated_at     TIMESTAMPTZ      NOT NULL default CURRENT_TIMESTAMP,
                      FOREIGN KEY (flight_id) REFERENCES flights (id),
                      FOREIGN KEY (passenger_id) REFERENCES passengers (id)
                  );
                """;
        try {
            Statement st = connection.createStatement();
            st.execute(createTableSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookingEntity create(BookingEntity bookingEntity) {
        Connection connection = connectionHelper.getConnection();
        String insertQuery = """
                INSERT INTO bookings(flight_id, passenger_id,booking_status,total_amount, created_at, updated_at)
                VALUES (?,?,?,?,?,?);
                """;
        try {
            connection.setAutoCommit(false);
            PreparedStatement prsForBooking = connection.prepareStatement(insertQuery);
            prsForBooking.setLong(1, bookingEntity.getFlight().getId());
            prsForBooking.setLong(2, bookingEntity.getPassenger().getId());
            prsForBooking.setString(3, bookingEntity.getBookingStatus().toString());
            prsForBooking.setDouble(4, bookingEntity.getTotalAmount());
            Timestamp createdAt = Timestamp.valueOf(bookingEntity.getCreatedAt());
            prsForBooking.setTimestamp(5, createdAt);
            Timestamp updatedAt = Timestamp.valueOf(bookingEntity.getUpdatedAt());
            prsForBooking.setTimestamp(6, updatedAt);
            prsForBooking.executeUpdate();
            connection.commit();
            prsForBooking.close();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return bookingEntity;
    }

    @Override
    public Collection<BookingEntity> getAll() {
        Connection connection = connectionHelper.getConnection();
        String selectAlternativeQuery = """
                SELECT b.id as booking_id,
                       b.created_at as b_created_at,
                       b.updated_at as b_updated_at, b.*,
                       f.id,
                       f.created_at as f_created_at,
                       f.updated_at as f_updated_at,
                       f.*,
                       p.id as p_id,
                       p.name,
                       p.surname,
                       p.email,
                       p.phone_number,
                       p.deleted,
                       p.created_at as p_created_at,
                       p.updated_at as p_updated_at
                FROM bookings b
                         LEFT JOIN passengers p on b.passenger_id = p.id
                         LEFT JOIN flights f ON b.flight_id = f.id;
                """;

        List<BookingEntity> bookingEntities = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            statement.execute(selectAlternativeQuery);
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                bookingEntities.add(EntityUtil.getBookingInfo(rs));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingEntities;
    }

    @Override
    public Optional<BookingEntity> getById(Long bookingId) {
        Connection connection = connectionHelper.getConnection();
        String selectAlternativeQuery = """
                SELECT b.id as booking_id,
                       b.created_at as b_created_at,
                       b.updated_at as b_updated_at, b.*,
                       f.id as flight_id,
                       f.created_at as f_created_at,
                       f.updated_at as f_updated_at,
                       f.*,
                       p.id as passenger_id,
                       p.name,
                       p.surname,
                       p.email,
                       p.phone_number,
                       p.deleted,
                       p.created_at as p_created_at,
                       p.updated_at as p_updated_at
                FROM bookings b
                         LEFT JOIN passengers p on p.id = b.passenger_id
                         LEFT JOIN flights f ON b.flight_id = f.id
                        WHERE b.id = ?;
                """;
        List<BookingEntity> bookings = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectAlternativeQuery)) {
            preparedStatement.setLong(1, bookingId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                bookings.add(EntityUtil.getBookingInfo(resultSet));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings.stream().findFirst();
    }

    @Override
    public BookingEntity deleteById(Long id) {
        Connection connection = connectionHelper.getConnection();
        String queryUpdate = """
                UPDATE bookings SET booking_status = ?, updated_at = ? WHERE id = ?;
                """;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setString(1, StatusMessage.CANCELLED.toString());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
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
        return getById(id).get();
    }

    @Override
    public BookingEntity update(Long id,BookingEntity bookingEntity) {
        Connection connection = connectionHelper.getConnection();
        String updateQuery = """
                    UPDATE bookings SET
                    flight_id = ?,
                    passenger_id = ?,
                    booking_status = ?,
                    updated_at = ?
                    WHERE id = ?;""";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setLong(1, bookingEntity.getFlight().getId());
            preparedStatement.setLong(2, bookingEntity.getPassenger().getId());
            preparedStatement.setLong(3, bookingEntity.getPassenger().getId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(bookingEntity.getUpdatedAt()));
            preparedStatement.setLong(5, id);
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
        return getById(bookingEntity.getId()).get();
    }

    @Override
    public boolean existsById(Long id) {
        Connection connection = connectionHelper.getConnection();
        String selectQuery = """
                Select* FROM bookings WHERE id = ?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}