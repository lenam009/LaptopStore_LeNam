package com.lenam.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lenam.laptopshop.domain.Order;
import com.lenam.laptopshop.domain.User;
import com.lenam.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = this.orderRepository.findAll();
        return orders;
    }

    public Order getOrderById(long id) {
        Optional<Order> order = this.orderRepository.findById(id);

        if (order.isPresent()) {
            return order.get();
        }

        return null;
    }

    public long getCountOrder() {
        return this.orderRepository.count();
    }

    public List<Order> getOrdersByUser(User user) {
        List<Order> orders = this.orderRepository.findByUser(user);
        return orders;
    }

    public void handleSaveOrder(Order order) {
        this.orderRepository.save(order);
    }

}
