package com.thoughtworks.parkinglot.service;

import com.thoughtworks.parkinglot.entity.Basement;
import com.thoughtworks.parkinglot.entity.Ticket;
import com.thoughtworks.parkinglot.model.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ParkingServiceTest {

    @Test
    public void testDemo() {
        assertEquals(1+1, 2);
    }

    @Test
    public void testCarParkingSuccess() {
        Car car = new Car("S1234");
        ParkingService parkingService = new ParkingService();
        Ticket ticket = parkingService.park(car);

        assertEquals(ticket.getLicense(), "S1234");
    }

}