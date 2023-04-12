package com.example.restaurant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
public class ItemPrimaryKey implements Serializable {
    @Column(name="order_id")
    @Getter
    @Setter
    private Integer orderId;
    @Column(name="menu_item_id")
    @Getter
    @Setter
    private Integer menuItemId;

    public ItemPrimaryKey() {
    }

    public ItemPrimaryKey(Integer orderId, Integer menuItemId) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
    }
}
