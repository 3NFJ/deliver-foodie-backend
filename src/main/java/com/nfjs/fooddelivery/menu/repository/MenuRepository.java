package com.nfjs.fooddelivery.menu.repository;

import com.nfjs.fooddelivery.menu.entity.Menu;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
    boolean existsByShopAndMenuNameEquals(Shop shop, String menuName);

    @Query("select m from Menu m where m.shop = ?1 and m.deletedAt is null")
    Page<Menu> findAllByShop(Shop shop, Pageable pageable);
}
