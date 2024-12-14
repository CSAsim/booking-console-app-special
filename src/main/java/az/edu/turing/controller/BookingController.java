package az.edu.turing.controller;

import az.edu.turing.service.inter.BookingService;

public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

}
