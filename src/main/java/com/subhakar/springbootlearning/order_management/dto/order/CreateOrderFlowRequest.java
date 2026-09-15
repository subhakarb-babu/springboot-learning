package com.subhakar.springbootlearning.order_management.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import java.util.UUID;

public class CreateOrderFlowRequest {

    @NotNull(message = "Customer id is required")
    private UUID customerId;

    @NotEmpty(message = "at least one Product is needed")
    private List<@Valid OrderItemRequest> items;

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}