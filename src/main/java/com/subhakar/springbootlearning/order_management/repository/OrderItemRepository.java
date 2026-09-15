package com.subhakar.springbootlearning.order_management.repository;

import com.subhakar.springbootlearning.order_management.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}