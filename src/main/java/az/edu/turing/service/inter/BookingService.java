package az.edu.turing.service.inter;

import az.edu.turing.model.dto.BookingDto;
import az.edu.turing.model.dto.request.booking.CreateBookingRequest;
import az.edu.turing.model.dto.request.booking.UpdateBookingRequest;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(CreateBookingRequest request);
    BookingDto getBooking(long id);
    BookingDto updateBooking(long id, UpdateBookingRequest request);
    void deleteBooking(long id);
    List<BookingDto> getAllBookings();

}
