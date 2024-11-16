package com.nfjs.fooddelivery.menu.entity;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
import com.nfjs.fooddelivery.menu.dto.MenuAddRequestDto;
import com.nfjs.fooddelivery.menu.dto.MenuUpdateRequestDto;
import com.nfjs.fooddelivery.menu.enums.MenuStatus;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "p_menus")
@Builder
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "menu_price", nullable = false)
    private int menuPrice;

    @Column(name = "menu_image")
    private String menuImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuStatus status;

    public void update(MenuUpdateRequestDto requestDto) {
        menuName = requestDto.menuName();
        menuPrice = requestDto.menuPrice();
        menuImage = requestDto.menuImage();
        status = requestDto.status();
    }
}
