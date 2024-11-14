package com.nfjs.fooddelivery.shop.repository;

import com.nfjs.fooddelivery.shop.entitiy.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;
import java.util.UUID;

public interface ShopRepository extends JpaRepository<Shop, UUID> {

    @Query("SELECT s FROM Shop s WHERE s.deletedAt IS NULL")
    Page<Shop> findAllNonDeletedShops(Pageable pageable);

    Optional<Shop> findByShopIdAndDeletedAtIsNull(UUID shopId);

}
