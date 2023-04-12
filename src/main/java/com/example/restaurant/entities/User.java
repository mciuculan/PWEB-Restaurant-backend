package com.example.restaurant.entities;

import com.example.restaurant.constants.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;
    @Getter
    @Setter
    @NonNull
    private String name;
    @Getter
    @Setter
    @NonNull
    private String email;
    @Getter
    @Setter
    @Column(name = "phone_number")
    @JsonProperty("phone_number")
    @NonNull
    private String phoneNumber;
    @Getter
    @Setter
    @NonNull
    private String password;
    @OneToMany
    @Getter
    @Setter
    @Transient
    private List<Reservation> reservations;
    @OneToMany
    @Getter
    @Setter
    @Transient
    private List<Order> orders;
    @Getter
    @Setter
    @NonNull
    private String role;

    public User() {
        this.role = Roles.CLIENT.toString();
        this.reservations = new ArrayList<>();
        this.orders = new ArrayList<>();
    }
}
