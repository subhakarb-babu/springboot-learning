package com.subhakar.springbootlearning.order_management.dto.order;

import java.math.BigDecimal;

public class UpdateOrderRequest {

    private String status;
    private BigDecimal totalAmount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}