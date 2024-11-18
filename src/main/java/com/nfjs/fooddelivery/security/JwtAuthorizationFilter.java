package com.nfjs.fooddelivery.security;

import com.nfjs.fooddelivery.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private final UserDetailsServiceImpl userDetailsService;
  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(
      HttpServletRequest req,
      HttpServletResponse res,
      FilterChain filterChain
  ) throws ServletException, IOException {

    log.debug("=== JwtAuthorizationFilter starting ===");
    String tokenValue = jwtUtil.getJwtFromHeader(req); // 토큰 추출

    if (StringUtils.hasText(tokenValue)) {
      try {
        jwtUtil.validateToken(tokenValue); // 토큰 유효성 검사
        Claims info = jwtUtil.getUserInfoFromToken(tokenValue); // 사용자 정보 추출
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(info.getSubject());

        if (!userDetails.getUser().isValidTokenCreatedAt()) {
          throw new AuthenticationException("로그아웃된 토큰입니다.") {};
        }

        setAuthentication(info.getSubject()); // 인증정보 설정

      } catch (AuthenticationException e) {
        throw e;
      }
    }
    filterChain.doFilter(req, res);
    log.debug("=== JwtAuthorizationFilter completed ===");
  }

  private boolean validateAndGetUser(String token) {
    if (!jwtUtil.validateToken(token)) {
      return false;
    }
    Claims info = jwtUtil.getUserInfoFromToken(token);
    setAuthentication(info.getSubject());
    return true;
  }

  /**
   * JWT 토큰에서 추출한 사용자 정보를 SecurityContext에 저장
   * @param username 인증할 사용자의 username
   */
  public void setAuthentication(String username) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    Authentication authentication = createAuthentication(username);
    context.setAuthentication(authentication);

    SecurityContextHolder.setContext(context);
  }

  /**
   * 사용자 정보를 기반으로 Authentication 객체를 생성함
   *
   * @param username 인증할 사용자의 username
   * @return 생성된 Authentication 객체
   */
  private Authentication createAuthentication(String username) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return new UsernamePasswordAuthenticationToken(
        userDetails,
        null,
        userDetails.getAuthorities()
    );
  }
}
