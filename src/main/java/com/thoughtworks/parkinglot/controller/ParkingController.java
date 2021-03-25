package com.thoughtworks.parkinglot.controller;

import com.thoughtworks.parkinglot.entity.Ticket;
import com.thoughtworks.parkinglot.model.Car;
import com.thoughtworks.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/parking")
class ParkingController {
    @Autowired
    ParkingService parkingService;

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @GetMapping("/park/{license}")
    public Ticket parkACar(@PathVariable("license") String license) {
        Car car = new Car(license);
        return this.parkingService.park(car);
    }
}
