package com.example.restaurant.controllers;

import com.example.restaurant.entities.Reservation;
import com.example.restaurant.entities.User;
import com.example.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> listAll() {
        List<User> users = userRepository.findAll();
        return users;
    }
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/user/{id}")
    public User getUserByID(@PathVariable Integer id) {
        User user = userRepository.getReferenceById(id);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserByID(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/user/{id}/reservations")
    public List<Reservation> getUserReservationsByUserID(@PathVariable Integer id) {
        User user = userRepository.getReferenceById(id);
        return user.getReservations();
    }

    @PostMapping("/user/{id}/add-reservation")
    public User addReservation(
            @PathVariable Integer id,
            @RequestBody Reservation reservation) {
        User user = userRepository.getReferenceById(id);
        user.getReservations().add(reservation);
        return user;
    }

    @GetMapping("/user/{id}/reservations/{reservationId}")
    public Reservation getUserReservationByID(
            @PathVariable Integer id,
            @PathVariable Integer reservationId) {
        User user = userRepository.getReferenceById(id);
        Reservation reservation = user.getReservations().get(reservationId);
        return reservation;
    }

}
