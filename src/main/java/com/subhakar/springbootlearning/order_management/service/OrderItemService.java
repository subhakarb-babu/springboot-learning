package com.subhakar.springbootlearning.order_management.service;


import com.subhakar.springbootlearning.order_management.dto.order_item.CreateOrderItemRequest;
import com.subhakar.springbootlearning.order_management.dto.order_item.OrderItemResponse;
import com.subhakar.springbootlearning.order_management.dto.order_item.UpdateOrderItemRequest;
import com.subhakar.springbootlearning.order_management.entity.Order;
import com.subhakar.springbootlearning.order_management.entity.OrderItem;
import com.subhakar.springbootlearning.order_management.entity.Product;

import com.subhakar.springbootlearning.order_management.exceptions.order.OrderNotFoundException;
import com.subhakar.springbootlearning.order_management.exceptions.order_item.OrderItemNotFoundException;
import com.subhakar.springbootlearning.order_management.exceptions.product.ProductNotFoundException;
import com.subhakar.springbootlearning.order_management.repository.OrderItemRepository;
import com.subhakar.springbootlearning.order_management.repository.OrderRepository;
import com.subhakar.springbootlearning.order_management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemService(
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository,
            ProductRepository productRepository) {

        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderItemResponse createOrderItem(CreateOrderItemRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() ->
                        new OrderNotFoundException("Order Not Found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException("Product Not Found"));

        OrderItem orderItem = new OrderItem();

        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(request.getQuantity());
        orderItem.setUnitPrice(request.getUnitPrice());

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return mapToResponse(savedOrderItem);
    }

    public OrderItemResponse getOrderItem(UUID id) {

        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() ->
                        new OrderItemNotFoundException("Order Item Not Found"));

        return mapToResponse(orderItem);
    }

    public List<OrderItemResponse> getAllOrderItems() {

        return orderItemRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OrderItemResponse updateOrderItem(
            UUID id,
            UpdateOrderItemRequest request) {

        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() ->
                        new OrderItemNotFoundException("Order Item Not Found"));

        if (request.getQuantity() != null) {
            existingOrderItem.setQuantity(request.getQuantity());
        }

        if (request.getUnitPrice() != null) {
            existingOrderItem.setUnitPrice(request.getUnitPrice());
        }

        OrderItem updatedOrderItem =
                orderItemRepository.save(existingOrderItem);

        return mapToResponse(updatedOrderItem);
    }

    public void deleteOrderItem(UUID id) {

        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() ->
                        new OrderItemNotFoundException("Order Item Not Found"));

        orderItemRepository.delete(existingOrderItem);
    }

    private OrderItemResponse mapToResponse(OrderItem orderItem) {

        OrderItemResponse response = new OrderItemResponse();

        response.setId(orderItem.getId());
        response.setOrderId(orderItem.getOrder().getId());
        response.setProductId(orderItem.getProduct().getId());
        response.setQuantity(orderItem.getQuantity());
        response.setUnitPrice(orderItem.getUnitPrice());

        return response;
    }
}