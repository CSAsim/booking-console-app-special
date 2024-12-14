package az.edu.turing.controller;

import az.edu.turing.service.inter.PassengerService;

public class PassengerController {

    private final PassengerService passengerService;


    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

}
