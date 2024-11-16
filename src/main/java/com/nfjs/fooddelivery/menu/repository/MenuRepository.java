package com.nfjs.fooddelivery.menu.repository;

import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
    boolean existsByShopAndMenuNameEquals(Shop shop, String menuName);

}
