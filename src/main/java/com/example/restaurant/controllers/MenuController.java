package com.example.restaurant.controllers;

import com.example.restaurant.entities.Menu;
import com.example.restaurant.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping()
    public List<Menu> listAll() {
        List<Menu> items = menuRepository.findAll();
        return items;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToMenu(@RequestBody Menu menuItem) {
        menuRepository.save(menuItem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItemFromMenu(@PathVariable Integer id) {
        menuRepository.deleteById(id);
    }
}
