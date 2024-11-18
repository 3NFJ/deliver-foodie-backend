package com.nfjs.fooddelivery.deliveryaddress.repository;

import com.nfjs.fooddelivery.deliveryaddress.entity.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, UUID> {
}
