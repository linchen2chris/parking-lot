package com.thoughtworks.parkinglot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean isAvailable;

    @Column
    private String license;

    @ManyToOne(fetch = FetchType.LAZY)
    private Basement basement;

    // constructor
    public Garage(boolean isAvailable) {
        this.isAvailable = isAvailable;
        this.license = null;
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
