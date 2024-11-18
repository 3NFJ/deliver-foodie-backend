package com.nfjs.fooddelivery.user.dto;

import com.nfjs.fooddelivery.user.entity.User;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdateUserResponseDto {
  private UUID userNumber;
  @Email
  private String email;
  private String username;
  private String nickname;
  private String phoneNumber;
  private LocalDateTime updatedAt;

  public static UpdateUserResponseDto fromEntity(User user){
    return UpdateUserResponseDto.builder()
        .username(user.getUsername())
        .email(user.getEmail())
        .nickname(user.getNickname())
        .phoneNumber(user.getPhoneNumber())
        .updatedAt(user.getUpdatedAt())
        .build();
  }
}
