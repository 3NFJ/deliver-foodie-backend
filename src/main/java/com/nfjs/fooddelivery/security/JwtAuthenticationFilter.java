package com.nfjs.fooddelivery.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfjs.fooddelivery.jwt.JwtUtil;
import com.nfjs.fooddelivery.user.dto.LoginRequestDto;
import com.nfjs.fooddelivery.user.entity.UserRoleEnum;
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

  public JwtAuthenticationFilter(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
    setFilterProcessesUrl("/api/auth/login");
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

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
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
    String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
    UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();

    String token = jwtUtil.createToken(username, role);
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
  }

  // TODO: 401 에러 메세지 추가 필요
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
    response.setStatus(401); // message 도 같이 보낼 수 있다
  }
}