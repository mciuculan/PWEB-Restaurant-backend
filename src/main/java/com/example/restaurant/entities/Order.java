package com.example.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    private Integer orderId;
    @Setter
    @Getter
    private Integer userId;
    @OneToMany
    @Getter
    @Setter
    @Transient
    private Set<Item> items;
    @Getter
    @Setter
    private Double total;

    public Order() {
        this.items = new HashSet<>();
    }

    public Order(Integer orderId, Integer userId) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = new HashSet<>();
    }
}
