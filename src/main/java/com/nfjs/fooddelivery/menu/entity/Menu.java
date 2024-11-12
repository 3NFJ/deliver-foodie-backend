package com.nfjs.fooddelivery.menu.entity;

import com.nfjs.fooddelivery.menu.enums.MenuStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "menus")
@Builder
public class Menu {
    @Id
    @GeneratedValue
    @Column(name = "menu_id", nullable = false)
    private UUID menuId;

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
