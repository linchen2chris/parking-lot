package com.thoughtworks.parkinglot.service;

import com.thoughtworks.parkinglot.entity.Basement;
import com.thoughtworks.parkinglot.entity.Garage;
import com.thoughtworks.parkinglot.entity.Ticket;
import com.thoughtworks.parkinglot.exception.NotFoundCarException;
import com.thoughtworks.parkinglot.exception.NotFoundTicket;
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

        //STEP_1: Find a basement then find garage
        Basement firstAvailableBasement = basementRepository.findFirstBasementByAvailable();
        Garage firstAvailableGarage = garageRepository.findFirstByBasementIDAndAvailableIsTrue(firstAvailableBasement.getId());

        //STEP_2: store a car
        firstAvailableGarage.setLicense(car.getLicense());
        firstAvailableGarage.setAvailable(false);
        firstAvailableBasement.setAvailable(firstAvailableBasement.getAvailable() - 1);

        garageRepository.save(firstAvailableGarage);
        basementRepository.save(firstAvailableBasement);

        //STEP_3: return a ticket
        Ticket ticket = new Ticket(car.getLicense(), firstAvailableGarage.getId(), firstAvailableBasement.getId());

        return ticketRepository.save(ticket);
    }

    public boolean pick(Long ticket_id) throws NotFoundCarException, NotFoundTicket {
        //Find the ticket(exist or not)
        Ticket findTicketByTicketId = ticketRepository.findTicketByTicketId(ticket_id);
        if (findTicketByTicketId == null) {
            throw new NotFoundTicket("Ticket Not Exist!");
        }
        //Find the car(exist or not)
        Garage findCarByBasementIdAndGarageId = garageRepository.findCarByBasementIdAndGarageId(findTicketByTicketId.getBasementId(), findTicketByTicketId.getGarageId());
        if (findCarByBasementIdAndGarageId != null) {
            // 试试拆出去 removeCarFromGarage();
            //delete from garage/basement, delete ticket
            findCarByBasementIdAndGarageId.setAvailable(true);
            findCarByBasementIdAndGarageId.setLicense(null);
            findCarByBasementIdAndGarageId.getBasement().setAvailable(findCarByBasementIdAndGarageId.getBasement().getAvailable() + 1);

            //save repo
            garageRepository.save(findCarByBasementIdAndGarageId);
            basementRepository.save(findCarByBasementIdAndGarageId.getBasement());
            ticketRepository.delete(findTicketByTicketId);
            return true;
        }
        throw new NotFoundCarException("Can Not Find This Car In Garage!");

    }
}
