package com.nfjs.fooddelivery.user.dto;

import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.entity.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class SignupRequestDto {

  @Email
  @NotBlank(message = "이메일을 입력해주세요.")
  private String email;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  private String password;

  @NotBlank(message = "이름을 입력해주세요.")
  private String username;

  @NotBlank(message = "닉네임을 입력해주세요.")
  private String nickname;

  @NotBlank(message = "전화번호를 입력해주세요.")
  private String phoneNumber;

  @NotNull
  private UserRoleEnum role;

  public User toEntity(BCryptPasswordEncoder encoder) {
    return User.builder()
        .email(this.email)
        .password(encoder.encode(password))
        .username(this.username)
        .nickname(this.nickname)
        .phoneNumber(this.phoneNumber)
        .role(this.role)
        .build();
  }
}
