package com.thoughtworks.parkinglot.service;

import com.thoughtworks.parkinglot.entity.Garage;
import com.thoughtworks.parkinglot.entity.Ticket;
import com.thoughtworks.parkinglot.model.Car;
import com.thoughtworks.parkinglot.repository.BasementRepository;
import com.thoughtworks.parkinglot.repository.GarageRepository;
import com.thoughtworks.parkinglot.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ParkingService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private GarageRepository garageRepository;
    @Autowired
    private BasementRepository basementRepository;

    public Ticket park(Car car) {

        Garage firstByAvailableIsTrue = garageRepository.findFirstByAvailableIsTrue();
        // Basement basement = firstByAvailableIsTrue.getBasement();

        firstByAvailableIsTrue.setLicense(car.getLicense());
        firstByAvailableIsTrue.setAvailable(false);
        // basement.setAvailable(basement.getAvailable() - 1);

        garageRepository.save(firstByAvailableIsTrue);
        // basementRepository.save(basement);
        Ticket ticket = new Ticket(car.getLicense(), firstByAvailableIsTrue.getId(), 1L);

        return ticketRepository.save(ticket);
    }
}
