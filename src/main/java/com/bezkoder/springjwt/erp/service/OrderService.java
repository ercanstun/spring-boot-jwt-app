package com.bezkoder.springjwt.erp.service;

import com.bezkoder.springjwt.erp.model.Order;
import com.bezkoder.springjwt.erp.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders(
            LocalDate start,
            LocalDate end
    ) {
        return orderRepository.findByOrderDateBetween(start, end);
    }
}
