package com.thoughtworks.parkinglot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String license;

    @Column
    private Long garageId;

    @Column
    private Long basementId;

    @Column
    private String staffId;


    public Ticket (String license, Long garageId, Long basementId, String staffId) {
        this.license = license;
        this.garageId = garageId;
        this.basementId = basementId;
        this.staffId = staffId;
    }
}
