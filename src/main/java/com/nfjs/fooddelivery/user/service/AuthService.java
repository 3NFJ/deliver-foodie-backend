package com.nfjs.fooddelivery.user.service;

import com.nfjs.fooddelivery.jwt.JwtUtil;
import com.nfjs.fooddelivery.user.dto.SigninResponseDto;
import com.nfjs.fooddelivery.user.dto.TokenReissueRequestDto;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;

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

  @Transactional
  public SigninResponseDto reissueToken(TokenReissueRequestDto request) {

    String refreshToken = request.getRefreshToken();
    jwtUtil.validateToken(refreshToken);

    Claims claims = jwtUtil.getUserInfoFromToken(refreshToken);
    UUID userNumber = UUID.fromString(claims.getSubject());

    User user = userRepository.findByUserNumber(userNumber)
        .orElseThrow(() -> new AuthenticationException("사용자 번호를 찾을 수 없습니다.") {
        });

    String newAccessToken = jwtUtil.createAccessToken(userNumber, user.getRole());
    String newRefreshToken = jwtUtil.createRefreshToken(userNumber, user.getRole());

    return SigninResponseDto.builder()
        .accessToken(newAccessToken)
        .refreshToken(newRefreshToken)
        .userNumber(userNumber)
        .role(user.getRole())
        .build();
  }
}
