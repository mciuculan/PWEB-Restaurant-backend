package com.example.restaurant.services;

import com.example.restaurant.entities.*;
import com.example.restaurant.exceptions.NoSuchElementException;
import com.example.restaurant.exceptions.UserNotFoundException;
import com.example.restaurant.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MenuRepository menuRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Reservation getUserReservationByID(Integer id, Integer reservationId) {
        User user = userRepository.getReferenceById(id);
        return user.getReservations().get(reservationId);
    }

    public User getUserByID(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("User with id " + id + " could not be found");
    }

    public List<Reservation> getUserReservationsByUserID(Integer id) throws NoSuchElementException {
        List<Reservation> userReservations = new ArrayList<>();
        List<Reservation> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            throw new NoSuchElementException("There are no reservations in the database!");
        }
        for (Reservation reservation : reservations) {
            if (id.equals(reservation.getUserId())) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }

    public List<Order> getOrdersByUserId(@PathVariable Integer id) throws NoSuchElementException {
        List<Order> userOrders = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new NoSuchElementException("There are no orders in the database!");
        }
        for (Order order : orders) {
            if (id.equals(order.getUserId())) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    @Transactional
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    public Reservation addReservation(Integer id, Reservation reservationDTO) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            // create table
            RestaurantTable table = new RestaurantTable();
            table.setTaken(true);
            table.setNumberOfSeats(reservationDTO.getTable().getNumberOfSeats());
            restaurantTableRepository.save(table);

            // create reservation
            Reservation reservation = new Reservation();
            reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
            reservation.setDateTime(reservationDTO.getDateTime());
            reservation.setTableId(table.getId());
            reservation.setTable(table);
            reservation.setUserId(id);
            reservation.setUser(user.get());
            reservationRepository.save(reservation);

            // update user
            List<Reservation> reservations = user.get().getReservations();
            reservations.add(reservation);
            user.get().setReservations(reservations);
            userRepository.save(user.get());
            return reservation;
        } else {
            throw new UserNotFoundException("Could not find user with id " + id);
        }
    }

    @Transactional
    public Order createOrder(Integer id, Order orderDTO) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            // create order
            Order order = new Order();
            order.setUserId(id);
            orderRepository.save(order);

            Set<Item> items = new HashSet<>();
            // create the Items
            for (Item item : orderDTO.getItems()) {
                Item newItem = new Item();
                ItemPrimaryKey itp = item.getItemPrimaryKey();
                itp.setOrderId(order.getOrderId());
                newItem.setItemPrimaryKey(itp);
                newItem.setMenuItem(menuRepository.getReferenceById(
                        itp.getMenuItemId()));
                newItem.setQuantity(item.getQuantity());
                items.add(newItem);
            }
            itemRepository.saveAll(items);

            order.setItems(items);
            Double total = 0.0;
            for (Item item : items) {
                total += item.getQuantity() * item.getMenuItem().getPrice();
            }
            order.setTotal(total);
            orderRepository.save(order);

            if (user.isPresent()) {
                // update user
                List<Order> userOrderList = user.get().getOrders();
                userOrderList.add(order);
                user.get().setOrders(userOrderList);
                userRepository.save(user.get());
            }
            return order;
        } else {
            throw new UserNotFoundException("Could not find user with id " + id);
        }
    }

    @Transactional
    public void deleteItemsByOrderId(Integer orderId) throws NoSuchElementException {
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty()) {
            throw new NoSuchElementException("There are no items in the database!");
        }
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item element = iterator.next();
            if (orderId.equals(element.getOrder().getOrderId())) {
                iterator.remove();
                itemRepository.delete(element);
            }
        }
        orderRepository.deleteById(orderId);
    }

    @Transactional
    public void deleteUserByID(Integer id) throws UserNotFoundException {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Could not find user with id " + id);
        }
    }
}
