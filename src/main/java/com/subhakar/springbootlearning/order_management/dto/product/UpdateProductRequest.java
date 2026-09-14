package com.subhakar.springbootlearning.order_management.dto.product;

import java.math.BigDecimal;

public class UpdateProductRequest{
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;

    public String getName(){
        return this.name;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public Integer getStockQuantity(){
        return this.stockQuantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}