package com.nfjs.fooddelivery.user.dto;

import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.entity.UserRoleEnum;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {
  private UUID userNumber;             // 사용자 고유 번호
  private String email;                // 이메일
  private String username;             // 사용자명
  private String nickname;             // 닉네임
  private String phoneNumber;          // 전화번호
  private UserRoleEnum role;           // 권한
  private String createdAt;            // 생성일시
  private String updatedAt;            // 수정일시

  public static UserResponseDto fromEntity(User user) {
    return UserResponseDto.builder()
        .userNumber(user.getUserNumber())
        .email(user.getEmail())
        .username(user.getUsername())
        .nickname(user.getNickname())
        .phoneNumber(user.getPhoneNumber())
        .role(user.getRole())
        .createdAt(user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        .updatedAt(user.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        .build();
  }
}
