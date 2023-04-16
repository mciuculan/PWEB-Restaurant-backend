package com.example.restaurant.constants;

public enum Roles {
    CLIENT("CLIENT"),
    ADMIN("ADMIN");

    private final String roleName;

    Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
