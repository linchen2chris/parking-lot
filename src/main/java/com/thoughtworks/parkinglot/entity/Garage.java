package com.thoughtworks.parkinglot.entity;

import javax.persistence.*;

@Entity
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private boolean isAvailable;

    @Column
    private String license;

    @Column
    private long basementId;

}
