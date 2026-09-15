package com.subhakar.springbootlearning.order_management.dto.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CreateOrderFlowResponse{

    private UUID orderId;
    private List<String> products;
    private BigDecimal totalAmount;
    private Integer totalQuantity;

    public CreateOrderFlowResponse(UUID orderId, List<String> productNames, BigDecimal totalAmount,Integer totalQuantity){
        this.orderId=orderId;
        this.products=productNames;
        this.totalAmount=totalAmount;
        this.totalQuantity=totalQuantity;
    }

    public List<String> getProducts() {
        return products;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}