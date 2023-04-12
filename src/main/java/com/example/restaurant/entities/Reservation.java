package com.example.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    private Integer id;
    @Setter
    @Getter
    @OneToOne(mappedBy = "reservation")
    private RestaurantTable table;
    @Setter
    @Getter
    private LocalDateTime dateTime;
    @Setter
    @Getter
    private Integer numberOfGuests;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @Setter
    @Getter
    private User user;

    public Reservation() {
    }

    public Reservation(Integer id,
                       RestaurantTable table,
                       LocalDateTime dateTime,
                       Integer numberOfGuests,
                       User user) {
        this.id = id;
        this.table = table;
        this.dateTime = dateTime;
        this.numberOfGuests = numberOfGuests;
        this.user = user;
    }
}
