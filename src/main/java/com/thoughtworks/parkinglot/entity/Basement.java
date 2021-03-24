package com.thoughtworks.parkinglot.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@NoArgsConstructor
public class Basement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long capacity;

    @Column
    private long available;

    public Basement(long capacity) {
        this.capacity = capacity;
    }

}
