package com.thoughtworks.parkinglot.controller;

import com.thoughtworks.parkinglot.entity.Ticket;
import com.thoughtworks.parkinglot.exception.NotFoundCarException;
import com.thoughtworks.parkinglot.exception.NotFoundTicket;
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

    @GetMapping("/pick_car/{ticket_id}")
    public String pickACar(@PathVariable("ticket_id") Long ticket_id) {
        try {
            if (this.parkingService.pick(ticket_id)) return "Pick Up Car Successfully";
        } catch (NotFoundCarException e) {
            return e.getExceptionMessage();
        } catch (NotFoundTicket e) {
            return e.getExceptionMessage();
        }
        return "Error";
    }
}
