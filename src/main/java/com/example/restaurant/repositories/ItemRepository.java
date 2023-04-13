package com.example.restaurant.repositories;

import com.example.restaurant.entities.Item;
import com.example.restaurant.entities.ItemPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, ItemPrimaryKey> {
}
