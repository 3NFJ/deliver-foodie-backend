package com.nfjs.fooddelivery.user.repository;

import com.nfjs.fooddelivery.user.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  Optional<User> findByUserNumber(UUID userNumber);

  @Query("SELECT u FROM User u WHERE u.userId = :userId AND u.deletedAt IS NULL")
  Optional<User> findByUserId(Long userId);

  boolean existsByEmail(String email);
}
