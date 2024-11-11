package com.nfjs.fooddelivery.menu.repository;

import com.nfjs.fooddelivery.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
}
