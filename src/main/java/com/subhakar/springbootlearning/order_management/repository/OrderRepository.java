package com.subhakar.springbootlearning.order_management.repository;

import com.subhakar.springbootlearning.order_management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}