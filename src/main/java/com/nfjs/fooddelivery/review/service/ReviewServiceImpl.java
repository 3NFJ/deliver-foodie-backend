package com.nfjs.fooddelivery.review.service;

import com.nfjs.fooddelivery.deliveryaddress.entity.DeliveryAddress;
import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.order.repository.OrderRepository;
import com.nfjs.fooddelivery.review.dto.*;
import com.nfjs.fooddelivery.review.entity.Review;
import com.nfjs.fooddelivery.review.repository.ReviewRepository;
import com.nfjs.fooddelivery.shop.entitiy.Shop;
import com.nfjs.fooddelivery.shop.repository.ShopRepository;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public ReviewCreateResponseDto createReview(ReviewCreateRequestDto reviewCreateRequestDto, UserDetails userDetails) {

        log.info("리뷰 등록 서비스 호출 : START");
        Shop shop = shopRepository.findById(reviewCreateRequestDto.getShopId()).orElseThrow();
        Order order = orderRepository.findById(reviewCreateRequestDto.getOrderId()).orElseThrow();
        User user = userRepository.findById(reviewCreateRequestDto.getUserId()).orElseThrow();
        DeliveryAddress deliveryAddress = user.getDeliveryAddressList().stream()
                .filter(DeliveryAddress::getIsDefault)
                .findFirst().orElseThrow();

        Review review = new Review(reviewCreateRequestDto,shop,order,user,deliveryAddress);
        reviewRepository.save(review);

        log.info("리뷰 등록 서비스 호출 : END");
        return new ReviewCreateResponseDto(review);
    }

    @Override
    @Transactional
    public ReviewModifyResponseDto modifyReview(UUID reviewId, ReviewModifyRequestDto reviewModifyRequestDto, UserDetails userDetails) {

        log.info("리뷰 수정 서비스 호출 : START");
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        review.modifyReview(reviewModifyRequestDto);

        log.info("리뷰 수정 서비스 호출 : END");
        return new ReviewModifyResponseDto(review);
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewGetResponseDto getReview(UUID reviewId, UserDetails userDetails) {

        log.info("리뷰 조회 서비스 호출 : START");
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        log.info("리뷰 조회 서비스 호출 : END");
        return new ReviewGetResponseDto(review);
    }
}
