package com.example.restaurant.controllers;

import com.example.restaurant.exceptions.NoSuchElementException;
import com.example.restaurant.exceptions.UserNotFoundException;
import com.example.restaurant.entities.*;
import com.example.restaurant.repositories.*;
import com.example.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/users")
    public ResponseEntity<?> listAll() {
        userService.getAllUsers();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}/reservations/{reservationId}")
    public ResponseEntity<?> getUserReservationByID(
            @PathVariable Integer id,
            @PathVariable Integer reservationId) {
        userService.getUserReservationByID(id, reservationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Integer id) throws UserNotFoundException {
        userService.getUserByID(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}/reservations")
    public ResponseEntity<?> getUserReservationsByUserID(@PathVariable Integer id) throws NoSuchElementException {
        userService.getUserReservationsByUserID(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}/orders")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Integer id) throws NoSuchElementException {
        userService.getOrdersByUserId(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{id}/add-reservation")
    public ResponseEntity<?> addReservation(
            @PathVariable Integer id,
            @RequestBody Reservation reservationDTO) throws UserNotFoundException {
        userService.addReservation(id, reservationDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/{id}/add-order")
    public ResponseEntity<?> createOrder(
            @PathVariable Integer id,
            @RequestBody Order orderDTO) throws UserNotFoundException {
        userService.createOrder(id, orderDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("order/{orderId}")
    public ResponseEntity<?> deleteOrderByID(
            @PathVariable Integer orderId) throws NoSuchElementException {
        userService.deleteItemsByOrderId(orderId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserByID(@PathVariable Integer id) throws UserNotFoundException {
        userService.deleteUserByID(id);
        return ResponseEntity.ok().build();
    }
}
