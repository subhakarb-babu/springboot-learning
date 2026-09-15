package com.subhakar.springbootlearning.order_management.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class OrderItemRequest {

    @NotNull(message = "Product id is required")
    private UUID productId;

    @NotNull
    @Min(value = 1, message = "Quantity should be grater then 0")
    private Integer quantity;

    public UUID getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }
}