package com.nfjs.fooddelivery.category.entity;

import com.nfjs.fooddelivery.category.dto.CategoryCreateRequestDto;
import com.nfjs.fooddelivery.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_categories")
public class Category extends BaseEntity {

    private static int displayOrderNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    public Category(CategoryCreateRequestDto categoryCreateRequestDto) {
        this.categoryName = categoryCreateRequestDto.getCategoryName();
        this.displayOrder = displayOrderNumber++;
    }
}
