package com.nfjs.fooddelivery.menu.entity;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
import com.nfjs.fooddelivery.menu.enums.MenuStatus;
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

    //todo Shop 엔티티 develop 반영 후 수정 필요
    @Column(name = "shop_id", nullable = false)
    private UUID shopId;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "menu_price", nullable = false)
    private int menuPrice;

    @Column(name = "menu_image")
    private String menuImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuStatus status;
}
