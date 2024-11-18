package com.nfjs.fooddelivery.review.repository;

import com.nfjs.fooddelivery.review.entity.Review;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository <Review, UUID> {

    Page<Review> findAllByShopOrderByCreatedAtDesc(Shop shop, Pageable pageable);

    @Query("SELECT ROUND(AVG(review.rating), 1) FROM Review review")
    Double averageRating();
}
