package com.example.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="restaurant_tables")
public class RestaurantTable {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private Integer numberOfSeats;
    @Getter
    @Setter
    private Boolean taken;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public RestaurantTable() {
    }

    public RestaurantTable(Integer id,
                           Integer numberOfSeats,
                           Boolean taken,
                           Reservation reservation) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
        this.taken = taken;
        this.reservation = reservation;
    }
}
