package com.thoughtworks.parkinglot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "customer")
@Getter
@Setter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String firstName;

	@Column
	private String lastName;

}
