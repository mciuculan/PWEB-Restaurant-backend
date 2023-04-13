package com.example.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue
    @Setter
    @Getter
    @Column(name="menu_item_id")
    private Integer menuItemId;
    @Setter
    @Getter
    @Column(name="item_name")
    @JsonProperty("item_name")
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
