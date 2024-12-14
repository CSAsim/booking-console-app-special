package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.domain.dao.inter.FlightDao;
import az.edu.turing.domain.dao.inter.PassengerDao;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.enums.BookingErrorMessage;
import az.edu.turing.enums.FLightErrorMessage;
import az.edu.turing.enums.PassengerErrorMessage;
import az.edu.turing.enums.StatusMessage;
import az.edu.turing.exception.booking.BookingNotFoundException;
import az.edu.turing.exception.flight.FlightNotFoundException;
import az.edu.turing.exception.passenger.PassengerNotFoundException;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.model.dto.BookingDto;
import az.edu.turing.model.dto.request.booking.CreateBookingRequest;
import az.edu.turing.model.dto.request.booking.UpdateBookingRequest;
import az.edu.turing.service.inter.BookingService;

import java.util.ArrayList;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final BookingMapper mapper;
    private final FlightDao flightDao;
    private final PassengerDao passengerDao;

    public BookingServiceImpl(BookingDao bookingDao,
                              BookingMapper mapper,
                              FlightDao flightDao,
                              PassengerDao passengerDao) {
        this.bookingDao = bookingDao;
        this.mapper = mapper;
        this.flightDao = flightDao;
        this.passengerDao = passengerDao;
    }

    @Override
    public BookingDto createBooking(CreateBookingRequest request) {
        FlightEntity flight = new FlightEntity();
        flight.setId(request.getFlightId());
        if(!flightDao.existsById(flight.getId())) {
            throw new FlightNotFoundException(FLightErrorMessage.NOT_FOUND);
        }
        PassengerEntity passenger = PassengerEntity.PassengerEntityBuilder.builder()
                .id(request.getPassengerId())
                .build();
        if(!passengerDao.existsById(passenger.getId())) {
            throw new PassengerNotFoundException(PassengerErrorMessage.NOT_FOUND);
        }
        BookingEntity bookingEntity = BookingEntity.BookingEntityBuilder.builder()
                .flight(flight)
                .passenger(passenger)
                .bookingStatus(StatusMessage.PENDING)
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .totalAmount(222.45)
                .build();
        return mapper.toDto(bookingDao.create(bookingEntity));
    }

    @Override
    public BookingDto getBooking(long id) {
        if(!bookingDao.existsById(id)) {
            throw new BookingNotFoundException(BookingErrorMessage.NOT_FOUND);
        }
        BookingEntity booking = bookingDao.getById(id).get();
        return mapper.toDto(bookingDao.create(booking));
    }

    @Override
    public BookingDto updateBooking(long id, UpdateBookingRequest request) {
        if (!bookingDao.existsById(id)) {
            throw new BookingNotFoundException(BookingErrorMessage.NOT_FOUND);
        }
        if(!flightDao.existsByFlightNumber(request.getFlightNumber())) {
            throw new FlightNotFoundException(FLightErrorMessage.NOT_FOUND_WITH_FLIGHT_NUMBER);
        }
        FlightEntity flight = flightDao.getByFlightNumber(request.getFlightNumber());
        if(!passengerDao.existsById(request.getPassengerId())) {
            throw new PassengerNotFoundException(PassengerErrorMessage.NOT_FOUND);
        }
        PassengerEntity passenger = passengerDao.getById(request.getPassengerId()).get();
        BookingEntity bookingEntity = BookingEntity.BookingEntityBuilder.builder()
                .id(id)
                .flight(flight)
                .passenger(passenger)
                .bookingStatus(request.getBookingStatus())
                .updatedAt(request.getUpdatedAt())
                .build();
        return mapper.toDto(bookingDao.update(id, bookingEntity));
    }

    @Override
    public void deleteBooking(long id) {
        if (!bookingDao.existsById(id)) {
            throw new BookingNotFoundException(BookingErrorMessage.NOT_FOUND);
        }
        bookingDao.deleteById(id);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<BookingEntity> bookings = bookingDao.getAll().stream().toList();
        List<BookingDto> bookingDto = new ArrayList<>();
        for (BookingEntity booking : bookings) {
            bookingDto.add(mapper.toDto(booking));
        }
        return bookingDto;
    }
}
