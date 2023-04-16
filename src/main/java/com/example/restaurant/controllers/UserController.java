package com.example.restaurant.controllers;

import com.example.restaurant.dto.UserDTO;
import com.example.restaurant.exceptions.NoSuchElementException;
import com.example.restaurant.exceptions.UserNotFoundException;
import com.example.restaurant.entities.*;
import com.example.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get/all")
    public ResponseEntity<?> listAll() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/get/{id}/reservations/{reservationId}")
    public ResponseEntity<?> getUserReservationByID(
            @PathVariable Integer id,
            @PathVariable Integer reservationId) {
        Reservation reservation = userService.getUserReservationByID(id, reservationId);
        return ResponseEntity.ok().body(reservation);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Integer id) throws UserNotFoundException {
        UserDTO user = userService.getUserByID(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/get/{id}/reservations")
    public ResponseEntity<?> getUserReservationsByUserID(@PathVariable Integer id) throws NoSuchElementException {
        List<Reservation> reservations = userService.getUserReservationsByUserID(id);
        return ResponseEntity.ok().body(reservations);
    }

    @GetMapping("/get/{id}/orders")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Integer id) throws NoSuchElementException {
        List<Order> orders = userService.getOrdersByUserId(id);
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/get/{id}/orders/items")
    public ResponseEntity<?> getOrdersItemsByUserId(@PathVariable Integer id) throws NoSuchElementException {
        List<Item> items = userService.getOrdersItemsByUserId(id);
        return ResponseEntity.ok().body(items);
    }

    @PostMapping("/{id}/add-reservation")
    public ResponseEntity<?> addReservation(
            @PathVariable Integer id,
            @RequestBody Reservation reservationDTO) throws UserNotFoundException {
        userService.addReservation(id, reservationDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/add-order")
    public ResponseEntity<?> createOrder(
            @PathVariable Integer id,
            @RequestBody Order orderDTO) throws UserNotFoundException {
        userService.createOrder(id, orderDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<?> deleteOrderByID(
            @PathVariable Integer orderId) {
        userService.deleteItemsByOrderId(orderId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserByID(@PathVariable Integer id) throws UserNotFoundException {
        userService.deleteUserByID(id);
        return ResponseEntity.ok().build();
    }
}
