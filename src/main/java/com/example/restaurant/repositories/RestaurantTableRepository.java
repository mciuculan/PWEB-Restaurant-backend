package com.example.restaurant.repositories;

import com.example.restaurant.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Integer> {
}
