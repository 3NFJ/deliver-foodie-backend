package com.nfjs.fooddelivery.order.repository;

import com.nfjs.fooddelivery.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository <Order, UUID> {
}
