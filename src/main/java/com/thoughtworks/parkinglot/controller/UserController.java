package com.thoughtworks.parkinglot.controller;

import java.util.Optional;
import com.thoughtworks.parkinglot.entity.User;
import com.thoughtworks.parkinglot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{id}")
	public User findById(@PathVariable("id") long id) {
		System.out.println("------->${id}" + id);

		Optional<User> user = this.userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@PostMapping("/register")
	public User createUser(@RequestBody() User user) {
		System.out.println("Line 26: " + user.toString());

		return this.userRepository.save(user);
	}

}
