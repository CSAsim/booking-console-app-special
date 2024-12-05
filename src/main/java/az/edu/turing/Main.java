package az.edu.turing;

import az.edu.turing.config.ConnectionHelper;
import az.edu.turing.domain.dao.impl.BookingDaoImpl;
import az.edu.turing.domain.dao.impl.FlightDaoImpl;
import az.edu.turing.domain.dao.impl.PassengerDaoImpl;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.enums.StatusMessage;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        BookingDaoImpl bookingDao = new BookingDaoImpl(new ConnectionHelper());
//        for (BookingEntity bookingEntity : bookingDao.getAll()) {
//            System.out.println(bookingEntity);
//        }
        System.out.println(bookingDao.getById(3L).get());

//        BookingEntity booking = BookingEntity.BookingEntityBuilder.builder()
//                .id(3L)
//                .flight(FlightEntity.FlightEntityBuilder.builder().id(2L).build())
//                .bookingStatus(StatusMessage.FAILED)
//                .updatedAt(LocalDateTime.now()).build();
//        System.out.println(bookingDao.update(booking));
//        System.out.println(bookingDao.deleteById(3L));

        FlightDaoImpl flightDao = new FlightDaoImpl(new ConnectionHelper());
//        System.out.println(flightDao.getBySpecially(null,null));
        System.out.println(flightDao.deleteById(2L));
        System.out.println(flightDao.getByFlightNumber("bb222"));


        PassengerDaoImpl passengerDao = new PassengerDaoImpl(new ConnectionHelper());
        System.out.println(passengerDao.getById(3L));
    }
}