package com.thoughtworks.parkinglot.service;

import com.thoughtworks.parkinglot.entity.*;
import com.thoughtworks.parkinglot.exception.NoAvailableGarageException;
import com.thoughtworks.parkinglot.exception.NoFreeStaffException;
import com.thoughtworks.parkinglot.exception.NotFoundCarException;
import com.thoughtworks.parkinglot.exception.NotFoundTicketException;
import com.thoughtworks.parkinglot.model.Car;
import com.thoughtworks.parkinglot.repository.*;
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
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private BoyRepository boyRepository;


    public Ticket park(Car car) throws NoFreeStaffException, NoAvailableGarageException {
        //Find a person to park the car
        Staff staff = staffRepository.pickAStaffRandomly();
        Basement basement = new Basement();
        if (managerRepository.findManagerById(staff.getId()) != null) {
            try { basement = findBasementNormally();
            } catch (NoAvailableGarageException e) { throw e; }
        } else {
            Boy boy = boyRepository.findParkingBoyById(staff.getId());
            if (boy.isSmart()) {
                /* park by smart parking boy */
                try { basement = findBasementSmartly();
                } catch (NoAvailableGarageException e) { throw e; }
            } else {
                try { basement = findBasementNormally();
                } catch (NoAvailableGarageException e) { throw e; }
            }
        }
        Garage firstAvailableGarage = garageRepository.findFirstByBasementIDAndAvailableIsTrue(basement.getId());

        //STEP_2: store a car
        firstAvailableGarage.setLicense(car.getLicense());
        firstAvailableGarage.setAvailable(false);
        basement.setAvailability(basement.getAvailability() - 1);

        garageRepository.save(firstAvailableGarage);
        basementRepository.save(basement);

        //STEP_3: return a ticket
        Ticket ticket = new Ticket(car.getLicense(), firstAvailableGarage.getId(), basement.getId(), staff.getId());

        return ticketRepository.save(ticket);
    }

    public Basement findBasementNormally() {
        Basement firstAvailableBasement = basementRepository.findFirstBasementByAvailable();
        if (firstAvailableBasement == null) {
            throw new NoAvailableGarageException("No Empty Garage for parking");
        }
        return firstAvailableBasement;
    }

    public Basement findBasementSmartly() {
        Basement mostAvailabilityBasement = basementRepository.findBasementWithMostAvailability();
        if (mostAvailabilityBasement == null) {
            throw new NoAvailableGarageException("No Empty Garage for parking");
        }
        return mostAvailabilityBasement;
    }

    public boolean pick(Long ticket_id) throws NotFoundCarException, NotFoundTicketException {
        //Find the ticket(exist or not)
        Ticket findTicketByTicketId = ticketRepository.findTicketByTicketId(ticket_id);
        if (findTicketByTicketId == null) {
            throw new NotFoundTicketException("Ticket Not Exist!");
        }
        //Find the car(exist or not)
        Garage findCarByBasementIdAndGarageId = garageRepository.findCarByBasementIdAndGarageId(findTicketByTicketId.getBasementId(), findTicketByTicketId.getGarageId());
        if (findCarByBasementIdAndGarageId != null) {
            // 试试拆出去 removeCarFromGarage();
            //delete from garage/basement, delete ticket
            findCarByBasementIdAndGarageId.setAvailable(true);
            findCarByBasementIdAndGarageId.setLicense(null);
            findCarByBasementIdAndGarageId.getBasement().setAvailability(findCarByBasementIdAndGarageId.getBasement().getAvailability() + 1);

            //save repo
            garageRepository.save(findCarByBasementIdAndGarageId);
            basementRepository.save(findCarByBasementIdAndGarageId.getBasement());
            ticketRepository.delete(findTicketByTicketId);
            return true;
        }
        throw new NotFoundCarException("Can Not Find This Car In Garage!");

    }

}
