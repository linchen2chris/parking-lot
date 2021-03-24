package com.thoughtworks.parkinglot.service;

import com.thoughtworks.parkinglot.entity.Ticket;
import com.thoughtworks.parkinglot.model.Car;

public class ParkingService {


    public Ticket park(Car car) {
        return new Ticket(car.getLicense());
    }
}
