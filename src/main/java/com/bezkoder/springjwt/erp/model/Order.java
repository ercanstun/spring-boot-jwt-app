package com.bezkoder.springjwt.erp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "orderNumber")
    private Long orderNumber;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @Column(name = "status")
    private String status;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
