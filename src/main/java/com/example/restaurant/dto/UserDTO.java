package com.example.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserDTO {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private List<String> roles;
}
