package com.nfjs.fooddelivery.user.controller;

import com.nfjs.fooddelivery.security.UserDetailsImpl;
import com.nfjs.fooddelivery.user.dto.SigninRequestDto;
import com.nfjs.fooddelivery.user.dto.SigninResponseDto;
import com.nfjs.fooddelivery.user.dto.SignupRequestDto;
import com.nfjs.fooddelivery.user.dto.SignupResponseDto;
import com.nfjs.fooddelivery.user.dto.TokenReissueRequestDto;
import com.nfjs.fooddelivery.user.service.AuthService;
import com.nfjs.fooddelivery.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;
  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<SignupResponseDto> signup(@RequestBody @Valid SignupRequestDto request) {
    SignupResponseDto response = userService.signupUser(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/signin")
  public ResponseEntity<Void> signin(@RequestBody @Valid SigninRequestDto request) {
    return ResponseEntity.ok().build();
  }

  @PostMapping("/signout")
  public ResponseEntity<String> logout(
      @AuthenticationPrincipal UserDetailsImpl userDetails) {

    if (userDetails == null) {
      throw new AuthenticationCredentialsNotFoundException("인증되지 않은 사용자입니다");
    }
    authService.expireToken(userDetails.getUser());

    return ResponseEntity.ok("로그아웃이 완료되었습니다.");
  }

  @PostMapping("/reissue")
  public ResponseEntity<SigninResponseDto> refreshToken(@RequestBody @Valid TokenReissueRequestDto request) {

    return ResponseEntity.ok(authService.reissueToken(request));
  }
}
