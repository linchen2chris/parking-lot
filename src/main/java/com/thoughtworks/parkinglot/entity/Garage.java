package com.thoughtworks.parkinglot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
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
    public Garage(boolean isAvailable, String license) {
        this.isAvailable = isAvailable;
        this.license = license;
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
