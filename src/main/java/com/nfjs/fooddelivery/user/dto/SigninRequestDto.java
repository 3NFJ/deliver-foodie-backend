package com.nfjs.fooddelivery.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SigninRequestDto {

  @NotBlank(message = "아이디 이름은 필수입니다.")
  private String username;

  @NotBlank(message = "비밀번호는 필수입니다.")
  private String password;
}
