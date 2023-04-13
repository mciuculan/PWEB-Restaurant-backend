package com.example.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
public class Item {
    @EmbeddedId
    @Setter
    @Getter
    private ItemPrimaryKey itemPrimaryKey;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="order_id",
            nullable = false,
            insertable=false,
            updatable=false)
    private Order order;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "menu_item_id",
            nullable = false,
            insertable=false,
            updatable=false)
    private Menu menuItem;
    @Getter
    @Setter
    private Integer quantity;
//    @Getter
//    @Setter
//    @Column(name="menu_item_id")
//    @JsonProperty("menu_item_id")
//    private Integer menuItemId;

    public Item() {
    }

    public Item(ItemPrimaryKey itemPrimaryKey,
                Order order,
                Integer quantity) {
        this.itemPrimaryKey = itemPrimaryKey;
        this.order = order;
        this.quantity = quantity;
    }
}
