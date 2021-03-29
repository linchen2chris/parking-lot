package com.thoughtworks.parkinglot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Basement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long capacity;

    @Column
    private long available;

    @OneToMany(mappedBy = "basement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Garage> garage_list = new ArrayList<>();

    // constructor
    public Basement(long capacity) {
        this.capacity = capacity;
        this.available = capacity;
        this.initAllGarage();
    }

    public void initAllGarage() {
        for (int i = 0; i < this.capacity; i++) {
            Garage garage = new Garage(true);
            garage_list.add(garage);
            garage.setBasement(this);
        }
    }

    public void addGarage(Garage garage) {
        garage_list.add(garage);
        garage.setBasement(this);
    }

    public void removeGarage(Garage garage) {
        garage_list.remove(garage);
        garage.setBasement(null);
    }

}
