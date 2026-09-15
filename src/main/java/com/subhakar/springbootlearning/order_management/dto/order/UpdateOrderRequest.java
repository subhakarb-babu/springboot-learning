package com.subhakar.springbootlearning.order_management.dto.order;

import com.subhakar.springbootlearning.order_management.enums.OrderStatus;

import java.math.BigDecimal;

public class UpdateOrderRequest {

    private OrderStatus status;
    private BigDecimal totalAmount;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}