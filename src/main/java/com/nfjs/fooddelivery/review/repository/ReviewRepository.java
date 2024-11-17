package com.nfjs.fooddelivery.review.repository;

import com.nfjs.fooddelivery.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository <Review, UUID> {
}
