package com.example.restaurant.dto;

import com.example.restaurant.entities.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    @Getter
    @Setter
    private Integer menuItemId;
    @Getter
    @Setter
    @JsonProperty("item_name")
    private String itemName;
    @Getter
    @Setter
    private Double price;

    public static MenuDTO fromMenu(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setMenuItemId(menu.getMenuItemId());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setItemName(menu.getItemName());
        return menuDTO;
    }
}
