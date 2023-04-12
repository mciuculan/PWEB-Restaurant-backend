package com.example.restaurant.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    private Integer menuItemId;
    @Setter
    @Getter
    private String itemName;
    @Setter
    @Getter
    private Double price;

    public Menu() {
    }

    public Menu(Integer menuItemId, String itemName, Double price) {
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.price = price;
    }
}
