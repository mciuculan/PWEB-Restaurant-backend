package com.example.restaurant.controllers;

import com.example.restaurant.entities.Reservation;
import com.example.restaurant.entities.User;
import com.example.restaurant.repositories.ReservationRepository;
import com.example.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ReservationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/reservations")
    public List<Reservation> listAll() {
        List<User> users = userRepository.findAll();
        List<Reservation> reservations = new ArrayList<>();
        for (User user : users) {
            reservations.addAll(user.getReservations());
        }
        return reservations;
    }
}
