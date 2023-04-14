package com.example.restaurant.entities;

import com.example.restaurant.constants.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Getter
    @Setter
    @NonNull
    private String name;
    @Getter
    @Setter
    @NonNull
    private String email;
    @Getter
    @Setter
    @Column(name = "phone_number")
    @JsonProperty("phone_number")
    @NonNull
    private String phoneNumber;
    @Getter
    @Setter
    @NonNull
    private String password;
    @OneToMany
    @Getter
    @Setter
    @Transient
    private List<Reservation> reservations;
    @OneToMany
    @Getter
    @Setter
    @Transient
    private List<Order> orders;
    @Getter
    @Setter
    @NonNull
    @Enumerated(EnumType.STRING)
    private Roles role;

    public User() {
        this.role = Roles.CLIENT;
        this.reservations = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
