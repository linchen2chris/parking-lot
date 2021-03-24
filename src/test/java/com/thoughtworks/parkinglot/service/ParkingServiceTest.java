package com.thoughtworks.parkinglot.service;

import com.thoughtworks.parkinglot.entity.Basement;
import com.thoughtworks.parkinglot.entity.Ticket;
import com.thoughtworks.parkinglot.model.Car;
import com.thoughtworks.parkinglot.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ParkingServiceTest {

    @Test
    public void testDemo() {
        assertEquals(1+1, 2);
    }

    @Test
    public void testCarParkingSuccess() {
        Basement basement = new Basement(50);
        Car car = new Car("S12344");

        TicketRepository mockTickRepo = mock(TicketRepository.class);
        when(mockTickRepo.save(Mockito.any(Ticket.class))).thenReturn(new Ticket("MockCar"));

        ParkingService parkingService = new ParkingService(mockTickRepo);
        Ticket ticket = parkingService.park(car);

        assertEquals(ticket.getLicense(), "MockCar");
    }


}