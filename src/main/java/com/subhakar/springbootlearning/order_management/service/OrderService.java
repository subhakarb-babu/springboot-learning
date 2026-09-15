package com.subhakar.springbootlearning.order_management.service;


import com.subhakar.springbootlearning.order_management.dto.order.CreateOrderRequest;
import com.subhakar.springbootlearning.order_management.dto.order.OrderResponse;
import com.subhakar.springbootlearning.order_management.dto.order.UpdateOrderRequest;
import com.subhakar.springbootlearning.order_management.entity.Customer;
import com.subhakar.springbootlearning.order_management.entity.Order;
import com.subhakar.springbootlearning.order_management.exceptions.customer.CustomerNotFoundException;
import com.subhakar.springbootlearning.order_management.exceptions.order.OrderNotFoundException;
import com.subhakar.springbootlearning.order_management.repository.CustomerRepository;
import com.subhakar.springbootlearning.order_management.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found"));

        Order order = new Order();

        order.setCustomer(customer);
        order.setStatus(request.getStatus());
        order.setTotalAmount(request.getTotalAmount());
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        return mapToResponse(savedOrder);
    }

    public OrderResponse getOrder(UUID id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order Not Found"));

        return mapToResponse(order);
    }

    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OrderResponse updateOrder(UUID id, UpdateOrderRequest request) {

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order Not Found"));

        if (request.getStatus() != null) {
            existingOrder.setStatus(request.getStatus());
        }

        if (request.getTotalAmount() != null) {
            existingOrder.setTotalAmount(request.getTotalAmount());
        }

        Order updatedOrder = orderRepository.save(existingOrder);

        return mapToResponse(updatedOrder);
    }

    public void deleteOrder(UUID id) {

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order Not Found"));

        orderRepository.delete(existingOrder);
    }

    private OrderResponse mapToResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setCustomerId(order.getCustomer().getId());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());

        return response;
    }
}