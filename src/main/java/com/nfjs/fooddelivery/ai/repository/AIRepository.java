package com.nfjs.fooddelivery.ai.repository;

import com.nfjs.fooddelivery.ai.entity.AI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AIRepository extends JpaRepository<AI, UUID> {
}
