package com.thoughtworks.parkinglot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingLotApplication {

	public static void main(String[] args) {
		System.out.println("Line 10: hello world");

		SpringApplication.run(ParkingLotApplication.class, args);
	}

}
