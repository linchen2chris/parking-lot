package com.thoughtworks.parkinglot.service;

import com.thoughtworks.parkinglot.entity.Basement;
import com.thoughtworks.parkinglot.entity.Ticket;
import com.thoughtworks.parkinglot.model.Car;
import com.thoughtworks.parkinglot.repository.BasementRepository;
import com.thoughtworks.parkinglot.repository.GarageRepository;
import com.thoughtworks.parkinglot.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ParkingServiceTest {

    @Mock
    TicketRepository mockTickRepository;

    @Mock
    GarageRepository garageRepository;
    @Mock
    BasementRepository basementRepository;

    @InjectMocks
    ParkingService parkingService;

    @Test
    public void testDemo() {
        assertEquals(1+1, 2);
    }

    @Test
    public void testCarParkingSuccess() {
        Car car = new Car("S12344");

        when(mockTickRepository.save(Mockito.any(Ticket.class))).thenReturn(new Ticket("MockCar", 1L, 2L));
        //when(garageRepository.save(Mockito.any(Garage.class))).thenReturn(new Garage(1L,false,  "MockCar", new Basement()));
        //when(garageRepository.findFirstByAvailableIsTrue()).thenReturn(new Garage(1L,false,  "MockCar", new Basement()));
        when(basementRepository.save(Mockito.any(Basement.class))).thenReturn(new Basement());

        Ticket ticket = parkingService.park(car);

        assertEquals(ticket.getLicense(), "MockCar");

        verify(mockTickRepository, only()).save(Mockito.any(Ticket.class));
        // verify(garageRepository, only()).save(Mockito.any(Garage.class));
    }
}