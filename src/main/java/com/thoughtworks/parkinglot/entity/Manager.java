package com.thoughtworks.parkinglot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Manager {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private boolean isFree;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Boy> boy_list = new ArrayList<>();

    public Manager(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isFree = true;
    }

    public void hireBoy(Boy boy) {
        boy_list.add(boy);
        boy.setManager(this);
    }

    public void fireBoy(Boy boy) {
        boy_list.remove(boy);
        boy.setManager(null);
    }

}
