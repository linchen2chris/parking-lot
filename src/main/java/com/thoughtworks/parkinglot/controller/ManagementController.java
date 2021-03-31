package com.thoughtworks.parkinglot.controller;

import com.thoughtworks.parkinglot.entity.Boy;
import com.thoughtworks.parkinglot.entity.Manager;
import com.thoughtworks.parkinglot.exception.NotEmptyBasementException;
import com.thoughtworks.parkinglot.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/management")
class ManagementController {
    @Autowired
    ManagementService managementService;

    @GetMapping("/hire_manager/{firstName}/{lastName}")
    public String hireAManager(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        Manager manager = new Manager(firstName, lastName);
        try {
            this.managementService.hireManager(manager);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return "Welcome The New Manager " + manager.getFirstName() + "!";
    }

    @GetMapping("/hire_boy/{manager_id}/{firstName}/{lastName}/{is_smart}")
    public String hireABoy(@PathVariable("manager_id") String manager_id, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("is_smart") boolean is_smart) {
        Boy boy = new Boy(firstName, lastName, is_smart);
        try {
            this.managementService.hireParkingBoy(manager_id, boy);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return "Welcome The New Parking Boy " + boy.getFirstName() + "!";
    }

    @GetMapping("/fire_boy/{manager_id}/{boy_id}")
    public String fireABoy(@PathVariable("manager_id") String manager_id, @PathVariable("boy_id") String boy_id) {
        String name;
        try {
            name = this.managementService.fireParkingBoy(manager_id, boy_id);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return "Already Fire " + name;
    }

    @GetMapping("/add_new_basement/{capacity}")
    public String addNewBasement(@PathVariable("capacity") long capacity) {
        try {
            this.managementService.addNewBasement(capacity);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return "Add a new basement with capacity: " + capacity;
    }

    @GetMapping("/remove_basement/{basement_id}")
    public String removeBasement(@PathVariable("basement_id") Long basement_id) {
        try {
            this.managementService.removeBasement(basement_id);
        } catch (NotEmptyBasementException e) {
            return e.getMessage();
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return "Already remove basement with ID: " + basement_id;
    }
}
