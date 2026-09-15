package com.subhakar.springbootlearning.order_management.dto.order_item;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class UpdateOrderItemRequest {

    @Positive
    private Integer quantity;

    @Positive
    private BigDecimal unitPrice;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}