package com.subhakar.springbootlearning.order_management.controller;


import com.subhakar.springbootlearning.order_management.dto.order_item.CreateOrderItemRequest;
import com.subhakar.springbootlearning.order_management.dto.order_item.OrderItemResponse;
import com.subhakar.springbootlearning.order_management.dto.order_item.UpdateOrderItemRequest;
import com.subhakar.springbootlearning.order_management.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItemResponse> createOrderItem(
            @Valid @RequestBody CreateOrderItemRequest request) {

        OrderItemResponse response =
                orderItemService.createOrderItem(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponse> getOrderItem(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                orderItemService.getOrderItem(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> getAllOrderItems() {

        return ResponseEntity.ok(
                orderItemService.getAllOrderItems()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderItemResponse> updateOrderItem(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateOrderItemRequest request) {

        return ResponseEntity.ok(
                orderItemService.updateOrderItem(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(
            @PathVariable UUID id) {

        orderItemService.deleteOrderItem(id);

        return ResponseEntity.noContent().build();
    }
}