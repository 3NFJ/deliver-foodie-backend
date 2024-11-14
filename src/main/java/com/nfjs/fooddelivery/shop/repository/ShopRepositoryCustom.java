package com.nfjs.fooddelivery.shop.repository;

import com.nfjs.fooddelivery.shop.entitiy.QShop;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.enums.ShopStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ShopRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QShop shop = QShop.shop;  // Q클래스

    public Optional<Shop> findValidShopById(UUID shopId) {

        return Optional.ofNullable(
                queryFactory.selectFrom(shop)
                        .where(shop.shopId.eq(shopId)
                                .and(shop.deletedAt.isNull())
                                .and(shop.shopStatus.eq(ShopStatus.OPEN)))
                        .fetchOne()
        );
    }
}
