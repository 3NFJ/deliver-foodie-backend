package com.nfjs.fooddelivery.order.dto;

import com.nfjs.fooddelivery.ordermenu.entity.OrderMenu;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderMenuDto {

    private UUID menuId;
    private String menuName;
    private Integer quantity;
    private Integer menuPrice;

    public OrderMenuDto(OrderMenu orderMenu) {
        this.menuId = orderMenu.getOrderMenuId();
        this.menuName = orderMenu.getMenuName();
        this.quantity = orderMenu.getQuantity();
        this.menuPrice = orderMenu.getMenuPrice();
    }
}
