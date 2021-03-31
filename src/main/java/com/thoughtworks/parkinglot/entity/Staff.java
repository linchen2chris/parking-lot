package com.thoughtworks.parkinglot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
public class Staff {
    @Id
    private String id;

    @Column
    private boolean isFree;

    public Staff(String id) {
        this.id = id;
        this.isFree = true;
    }
}
