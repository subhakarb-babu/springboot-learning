package com.subhakar.springbootlearning.order_management.dto.product;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductResponse {

    private UUID id;
    private String name;
    private BigDecimal price;
    private int stockQuantity;

    public CreateProductResponse(UUID id, String name, BigDecimal price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price=price;
        this.stockQuantity=stockQuantity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}