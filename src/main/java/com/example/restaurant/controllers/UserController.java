package com.example.restaurant.controllers;

import com.example.restaurant.entities.Reservation;
import com.example.restaurant.entities.RestaurantTable;
import com.example.restaurant.entities.User;
import com.example.restaurant.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @GetMapping("/users")
    public List<User> listAll() {
        List<User> users = userRepository.findAll();
        return users;
    }
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
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
        List<Reservation> userReservations = new ArrayList<>();
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            if (reservation.getUserId().equals(id)) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }

    @PostMapping("/user/{id}/add-reservation")
    public ResponseEntity<?> addReservation(
            @PathVariable Integer id,
            @RequestBody Reservation reservationDTO) {
        User user = userRepository.getReferenceById(id);

        // create table
        RestaurantTable table = new RestaurantTable();
        table.setTaken(true);
        table.setNumberOfSeats(reservationDTO.getTable().getNumberOfSeats());
        restaurantTableRepository.save(table);

        // create reservation
        Reservation reservation = new Reservation();
        reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
        reservation.setDateTime(reservationDTO.getDateTime());
        reservation.setTableId(table.getId());
        reservation.setTable(table);
        reservation.setUserId(id);
        reservation.setUser(user);
        reservationRepository.save(reservation);

        // update user
        List<Reservation> reservations = user.getReservations();
        reservations.add(reservation);
        user.setReservations(reservations);
        userRepository.save(user);

        return ResponseEntity.ok().build();
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
