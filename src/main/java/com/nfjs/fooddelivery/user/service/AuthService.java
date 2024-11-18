package com.nfjs.fooddelivery.user.service;

import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {
  private final UserRepository userRepository;

  @Transactional
  public void updateTokenCreatedAt(User user) {
    user.updateTokenCreatedAt(LocalDateTime.now());
    userRepository.save(user);
  }

  @Transactional
  public void expireToken(User user) {
    user.updateTokenCreatedAt(LocalDateTime.now().minusDays(7));
    userRepository.save(user);
  }
}
