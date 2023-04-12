package com.example.restaurant.entities;

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
            referencedColumnName = "menuItemId",
            nullable = false,
            insertable=false,
            updatable=false)
    private Menu menuItem;
    @Getter
    @Setter
    private Integer quantity;

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
