package com.nfjs.fooddelivery.ordermenu.repository;

import com.nfjs.fooddelivery.ordermenu.entity.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderMenuRepository extends JpaRepository <OrderMenu, UUID> {
}