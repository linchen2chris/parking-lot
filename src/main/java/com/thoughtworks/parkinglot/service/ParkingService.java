package com.thoughtworks.parkinglot.service;

import com.thoughtworks.parkinglot.entity.Ticket;
import com.thoughtworks.parkinglot.model.Car;
import com.thoughtworks.parkinglot.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class ParkingService {

    @Autowired
    private TicketRepository ticketRepository;
    //private Basement basement;
    //private Garage garage;

    public Ticket park(Car car) {
        Ticket ticket = new Ticket(car.getLicense());
        //basement
        //garage
        return ticketRepository.save(ticket);
    }

   // public void reduceBasementCapacity() {
   //     this.basement.setAvailable();
   // }
}
