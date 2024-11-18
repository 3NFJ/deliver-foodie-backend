package com.nfjs.fooddelivery.order.repository;

import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository <Order, UUID> {

    List<Order> findAllByShopOrderByCreatedAtDesc(Shop shop);

    Page<Order> findAllByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}