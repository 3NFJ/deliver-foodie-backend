package com.nfjs.fooddelivery.ordermenu.entity;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
import com.nfjs.fooddelivery.order.entity.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "p_order_menus")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderMenuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "menu_id", nullable = false)
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "menu_id", nullable = false)
    // UUID Type -> Menu Type 변경 예정
    private UUID menu;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "menu_price", nullable = false)
    private Integer menuPrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    public OrderMenu(Order order, UUID menu, Integer quantity) {
        this.order = order;
        this.menu = menu;
        this.menuName = "메뉴 이름"; // 추후 menu.getMenuName(); 대체
        this.menuPrice = 25000; // 추후 menu.getMenuPrice(); 대체
        this.quantity = quantity;
        this.totalPrice = 25000*2; // 추후 총 가격 메소드로 대체
    }
}
