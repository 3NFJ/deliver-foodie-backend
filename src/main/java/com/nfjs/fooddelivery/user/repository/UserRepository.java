package com.nfjs.fooddelivery.user.repository;

import com.nfjs.fooddelivery.user.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  boolean existsByEmail(String email);
  Optional<User> findByUserNumber(UUID userNumber);
}
