package com.thoughtworks.parkinglot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String license;

    @Column
    private Long garageID;

    @Column
    private Long basementID;


    public Ticket (String license, Long garageID, Long basementID) {
        this.license = license;
        this.garageID = garageID;
        this.basementID = basementID;
    }
}
