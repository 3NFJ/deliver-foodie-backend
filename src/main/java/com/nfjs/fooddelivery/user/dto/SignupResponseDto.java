package com.nfjs.fooddelivery.user.dto;

import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.entity.UserRoleEnum;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SignupResponseDto {

  private UUID userNumber;
  private String email;
  private String nickname;
  private String phoneNumber;
  private UserRoleEnum role;
  private LocalDateTime createdAt;

  public static SignupResponseDto fromEntity(User user) {
    return SignupResponseDto.builder()
        .userNumber(user.getUserNumber())
        .email(user.getEmail())
        .nickname(user.getNickname())
        .phoneNumber(user.getPhoneNumber())
        .role(user.getRole())
        .createdAt(user.getCreatedAt())
        .build();
  }
}
