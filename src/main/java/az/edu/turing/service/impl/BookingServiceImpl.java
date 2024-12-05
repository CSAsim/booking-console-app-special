package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.inter.BookingDao;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.model.dto.BookingDto;
import az.edu.turing.model.dto.request.booking.CreateBookingRequest;
import az.edu.turing.model.dto.request.booking.UpdateBookingRequest;
import az.edu.turing.service.inter.BookingService;

import java.util.List;

public class BookingServiceImpl implements BookingService {



    private final BookingDao bookingDao;
    private final BookingMapper mapper;

    public BookingServiceImpl(BookingDao bookingDao, BookingMapper mapper) {
        this.bookingDao = bookingDao;
        this.mapper = mapper;
    }

    @Override
    public BookingDto createBooking(CreateBookingRequest request) {
        return null;
    }

    @Override
    public BookingDto getBooking(long id) {
        return null;
    }

    @Override
    public BookingDto updateBooking(long id, UpdateBookingRequest request) {
        return null;
    }

    @Override
    public void deleteBooking(long id) {

    }

    @Override
    public List<BookingDto> getAllBookings() {
        return List.of();
    }
}
