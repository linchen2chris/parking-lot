package com.thoughtworks.parkinglot.entity;

import javax.persistence.*;

@Entity
public class Basement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long capacity;

    @Column
    private long available;
}
