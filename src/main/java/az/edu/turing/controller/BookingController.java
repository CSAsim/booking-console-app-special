package az.edu.turing.controller;

import az.edu.turing.model.dto.BookingDto;
import az.edu.turing.model.dto.request.booking.CreateBookingRequest;
import az.edu.turing.model.dto.request.booking.UpdateBookingRequest;
import az.edu.turing.service.inter.BookingService;

import java.util.List;

public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingDto create(CreateBookingRequest request) {
        return bookingService.createBooking(request);
    }

    public List<BookingDto> getAll() {
        return bookingService.getAllBookings();
    }

    public BookingDto getById(long id) {
        return bookingService.getBooking(id);
    }

    public BookingDto update(long id, UpdateBookingRequest request) {
        return bookingService.updateBooking(id, request);
    }

    public void delete(long id) {
        bookingService.deleteBooking(id);
    }
}
