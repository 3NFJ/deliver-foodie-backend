package com.nfjs.fooddelivery.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfjs.fooddelivery.common.excetpion.ErrorResponse;
import com.nfjs.fooddelivery.jwt.JwtUtil;
import com.nfjs.fooddelivery.user.dto.SigninRequestDto;
import com.nfjs.fooddelivery.user.dto.SigninResponseDto;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.entity.UserRoleEnum;
import com.nfjs.fooddelivery.user.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final JwtUtil jwtUtil;
  private final AuthService authService;

  public JwtAuthenticationFilter(JwtUtil jwtUtil, AuthService authService) {
    this.jwtUtil = jwtUtil;
    this.authService = authService;
    setFilterProcessesUrl("/api/auth/signin");
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      SigninRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(),
          SigninRequestDto.class);

      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(
              requestDto.getUsername(),
              requestDto.getPassword(),
              null
          )
      );
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult)
      throws IOException {
    String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
    UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();
    User user = ((UserDetailsImpl) authResult.getPrincipal()).getUser();

    authService.updateTokenCreatedAt(user); // 인증 성공 후 토큰 업데이트

    String accessToken = jwtUtil.createAccessToken(username, role);
    String refreshToken = jwtUtil.createRefreshToken(username, role);

    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);

    SigninResponseDto signinResponseDto = SigninResponseDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .role(role)
        .build();

    response.setContentType("application/json");
    new ObjectMapper().writeValue(response.getWriter(), signinResponseDto);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed)
      throws IOException {

    ErrorResponse errorResponse = new ErrorResponse(
        HttpServletResponse.SC_UNAUTHORIZED,  // 인증 실패한 경우 401
        "인증에 실패했습니다"
    );

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    new ObjectMapper().writeValue(response.getWriter(), errorResponse);
  }
}
