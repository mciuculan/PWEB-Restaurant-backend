package com.example.restaurant.services;

import com.example.restaurant.dto.MenuDTO;
import com.example.restaurant.entities.Menu;
import com.example.restaurant.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<MenuDTO> getMenu() {
        List<Menu> items = menuRepository.findAll();
        List<MenuDTO> itemsDTO = new ArrayList<>();

        items.forEach(item -> itemsDTO.add(MenuDTO.fromMenu(item)));

        return itemsDTO;
    }

    public MenuDTO addToMenu(Menu menuItem) {
        menuRepository.save(menuItem);
        return MenuDTO.fromMenu(menuItem);
    }

    public void deleteItemFromMenu(@PathVariable Integer id) {
        menuRepository.deleteById(id);
    }
}
