package com.nfjs.fooddelivery.user.dto;

import com.nfjs.fooddelivery.user.entity.UserRoleEnum;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SigninResponseDto {

  private String accessToken;
  private String refreshToken;
  private UUID userNumber;
  private UserRoleEnum role;
}
