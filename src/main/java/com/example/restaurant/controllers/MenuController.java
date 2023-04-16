package com.example.restaurant.controllers;

import com.example.restaurant.dto.MenuDTO;
import com.example.restaurant.entities.Menu;
import com.example.restaurant.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping()
    public  ResponseEntity<List<MenuDTO>> listAll() {
        List<MenuDTO> items = menuService.getMenu();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToMenu(@RequestBody Menu menuItem) {
        MenuDTO menuDTO = menuService.addToMenu(menuItem);
        return ResponseEntity.ok(menuDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItemFromMenu(@PathVariable Integer id) {
        menuService.deleteItemFromMenu(id);
        return ResponseEntity.ok().build();
    }
}
