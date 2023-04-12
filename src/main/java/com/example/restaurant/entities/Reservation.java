package com.example.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Setter
    @Getter
    @Column(name = "reservation_id")
    private Integer id;
    @Setter
    @Getter
    @OneToOne(mappedBy = "res")
    @Transient
    private RestaurantTable table;
    @Setter
    @Getter
    @Column(name = "table_id")
    private Integer tableId;
    @Setter
    @Getter
    @Column(name = "user_id")
    private Integer userId;
    @Setter
    @Getter
    private LocalDateTime dateTime;
    @Setter
    @Getter
    private Integer numberOfGuests;
    @ManyToOne
    @JoinColumn(name="user_id",
            nullable = false,
            insertable=false,
            updatable=false)
    @Setter
    @Getter
    @Transient
    private User user;

    public Reservation() {
    }
}
