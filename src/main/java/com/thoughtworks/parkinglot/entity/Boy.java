package com.thoughtworks.parkinglot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Boy {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private boolean isFree;

    @Column
    private boolean isSmart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manager manager;

    // constructor
    public Boy(String firstName, String lastName, boolean isSmart) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isFree = true;
        this.isSmart = isSmart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Garage )) return false;
        return id != null && id.equals(((Garage) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
