package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.erp.model.Order;
import com.bezkoder.springjwt.erp.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/erp")
public class ErpController {

    private final OrderService orderService;

    public ErpController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> getOrders(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return orderService.getOrders(startDate, endDate);
    }
}
