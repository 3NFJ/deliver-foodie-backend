package com.nfjs.fooddelivery.user.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequestDto {

  @Email
  private String email;
  private String password;
  private String username;
  private String nickname;
  private String phoneNumber;

}
