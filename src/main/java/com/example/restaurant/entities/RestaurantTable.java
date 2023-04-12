package com.example.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="restaurant_tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Getter
    @Setter
    @Column(name = "table_id")
    private Integer id;
    @Getter
    @Setter
    private Integer numberOfSeats;
    @Getter
    @Setter
    private Boolean taken = false;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "reservation_id")
    @Transient
    private Reservation res;

    public RestaurantTable() {
    }
}
