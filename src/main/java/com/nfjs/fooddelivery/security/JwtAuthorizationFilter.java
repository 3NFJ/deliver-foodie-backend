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
    String tokenValue = jwtUtil.getJwtFromHeader(req);

    if (StringUtils.hasText(tokenValue)) {
      Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
      UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(info.getSubject());

      if (!userDetails.getUser().isValidTokenCreatedAt()) {
        log.error("Logged out token");
        return;
      }

      if (!jwtUtil.validateToken(tokenValue)) {
        log.error("Token Error");
        return;
      }

      try {
        setAuthentication(info.getSubject());
      } catch (Exception e) {
        log.error(e.getMessage());
        return;
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

  // 인증 처리
  public void setAuthentication(String username) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    Authentication authentication = createAuthentication(username);
    context.setAuthentication(authentication);

    SecurityContextHolder.setContext(context);
  }

  // 인증 객체 생성
  private Authentication createAuthentication(String username) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }
}
