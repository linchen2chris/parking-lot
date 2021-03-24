package com.thoughtworks.parkinglot.controller;

import java.util.Optional;
import com.thoughtworks.parkinglot.entity.Customer;
import com.thoughtworks.parkinglot.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/{id}")
	public Customer findById(@PathVariable("id") long id) {
		System.out.println("------->${id}" + id);

		Optional<Customer> customer = this.customerRepository.findById(id);
		if(customer.isPresent()) {
			return customer.get();
		} else {
			return null;
		}
	}

	@PostMapping("/register")
	public Customer createCustomer(@RequestBody() Customer customer) {
		System.out.println("Line 26: " + customer.toString());

		return this.customerRepository.save(customer);
	}

}
