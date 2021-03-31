package com.thoughtworks.parkinglot.service;

import com.thoughtworks.parkinglot.entity.*;
import com.thoughtworks.parkinglot.exception.NotEmptyBasementException;
import com.thoughtworks.parkinglot.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ManagementService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private BoyRepository boyRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private BasementRepository basementRepository;

    @Autowired
    private GarageRepository garageRepository;

    public void hireManager(Manager manager) {
        Staff staff = new Staff(manager.getId());
        staffRepository.save(staff);
        managerRepository.save(manager);
    }

    public void hireParkingBoy(String manager_id, Boy boy) {
         Manager manager = managerRepository.findManagerById(manager_id);
         manager.hireBoy(boy);
         Staff staff = new Staff(boy.getId());
         boyRepository.save(boy);
         staffRepository.save(staff);
    }

    public String fireParkingBoy(String manager_id, String boy_id) {
        Manager manager = managerRepository.findManagerById(manager_id);
        Boy boy = boyRepository.findParkingBoyById(boy_id);
        String name = boy.getFirstName();
        Staff staff = staffRepository.findStaffById(boy_id);
        manager.fireBoy(boy);
        staffRepository.delete(staff);
        boyRepository.delete(boy);
        return name;
    }

    public void addNewBasement(long capacity) {
        Basement basement = new Basement(capacity);
        basementRepository.save(basement);
        for (final Garage g: basement.getGarage_list()) {
            garageRepository.save(g);
        }
    }

    public void removeBasement(Long basement_id) {
        Basement basement = basementRepository.findBasementById(basement_id);
        if (basement.getAvailability() != basement.getCapacity()) {
            throw new NotEmptyBasementException("Basement Not Empty! Cannot Delete It!");
        }
        for (final Garage g: basement.getGarage_list()) {
            garageRepository.delete(g);
        }
        basementRepository.delete(basement);
    }
}
