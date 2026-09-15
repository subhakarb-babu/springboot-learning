package com.subhakar.springbootlearning.order_management.service;


import com.subhakar.springbootlearning.order_management.dto.order.*;
import com.subhakar.springbootlearning.order_management.entity.Customer;
import com.subhakar.springbootlearning.order_management.entity.Order;
import com.subhakar.springbootlearning.order_management.entity.OrderItem;
import com.subhakar.springbootlearning.order_management.entity.Product;
import com.subhakar.springbootlearning.order_management.enums.OrderStatus;
import com.subhakar.springbootlearning.order_management.exceptions.customer.CustomerNotFoundException;
import com.subhakar.springbootlearning.order_management.exceptions.order.OrderNotFoundException;
import com.subhakar.springbootlearning.order_management.exceptions.product.DuplicateProductException;
import com.subhakar.springbootlearning.order_management.exceptions.product.ProductNotFoundException;
import com.subhakar.springbootlearning.order_management.exceptions.product.ProductOutOfStockException;
import com.subhakar.springbootlearning.order_management.repository.CustomerRepository;
import com.subhakar.springbootlearning.order_management.repository.OrderItemRepository;
import com.subhakar.springbootlearning.order_management.repository.OrderRepository;
import com.subhakar.springbootlearning.order_management.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ProductRepository productRepository,
                        OrderItemRepository orderItemRepository,
                        CustomerService customerService
    ) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository=productRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerService=customerService;
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

    @Transactional
    public CreateOrderFlowResponse createOrderFlow(CreateOrderFlowRequest request) {

        Customer customer = customerService.getCustomer(request.getCustomerId());

        BigDecimal totalAmount = BigDecimal.ZERO;
        int totalQuantity = 0;

        List<String> productNames = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Set<UUID> productIds = new HashSet<>();

        // Validate products and calculate order totals
        for (OrderItemRequest item : request.getItems()) {

            Product product = getAndValidateProduct(item, productIds);

            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            totalAmount = totalAmount.add(itemTotal);
            totalQuantity += item.getQuantity();

            productNames.add(product.getName());
            products.add(product);
        }

        // Create order
        Order order = new Order(customer,OrderStatus.PLACED,totalAmount,LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        // Create order items and update stock
        for (int i = 0; i < request.getItems().size(); i++) {

            OrderItemRequest item = request.getItems().get(i);
            Product product = products.get(i);

            product.setStockQuantity(
                    product.getStockQuantity() - item.getQuantity()
            );

            OrderItem orderItem = new OrderItem(savedOrder,product,item.getQuantity(),product.getPrice());
            orderItemRepository.save(orderItem);
        }

        return new CreateOrderFlowResponse(
                savedOrder.getId(),
                productNames,
                totalAmount,
                totalQuantity
        );
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
    private Product getAndValidateProduct(
            OrderItemRequest item,
            Set<UUID> productIds) {

        if (!productIds.add(item.getProductId())) {
            throw new DuplicateProductException(
                    "Duplicate product in order: " + item.getProductId()
            );
        }

        Product product = productRepository
                .findById(item.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException("Product Not Found"));

        if (product.getStockQuantity() < item.getQuantity()) {
            throw new ProductOutOfStockException(
                    "Product is Out of Stock: " + product.getName()
            );
        }

        return product;
    }
}