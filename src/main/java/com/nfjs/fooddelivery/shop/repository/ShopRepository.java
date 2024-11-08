package com.nfjs.fooddelivery.shop.repository;

import com.nfjs.fooddelivery.shop.entitiy.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopRepository extends JpaRepository<Shop, UUID> {
}
