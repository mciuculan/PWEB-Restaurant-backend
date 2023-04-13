package com.example.restaurant.controllers;

import com.example.restaurant.repositories.ItemRepository;
import com.example.restaurant.repositories.OrderRepository;
import com.example.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;


}
