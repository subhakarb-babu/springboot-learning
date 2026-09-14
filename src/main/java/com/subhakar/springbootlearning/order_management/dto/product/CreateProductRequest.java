package com.subhakar.springbootlearning.order_management.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateProductRequest{

    @NotBlank(message = "Product name is required")
    @Size(max = 255)
    private String name;

    @Min(value = 10)
    private BigDecimal price;

    @Min(value = 1)
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

    public void setName(String name){
        this.name=name;
    }
    public void setPrice(BigDecimal price){
        this.price=price;
    }
    public void setStockQuantity(Integer stockQuantity){
        this.stockQuantity=stockQuantity;
    }
}