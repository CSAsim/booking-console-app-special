package az.edu.turing.controller;

import az.edu.turing.service.inter.FlightService;

public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

}
