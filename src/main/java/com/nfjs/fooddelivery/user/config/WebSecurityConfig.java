package com.nfjs.fooddelivery.user.config;

import com.nfjs.fooddelivery.jwt.JwtUtil;
import com.nfjs.fooddelivery.security.JwtAuthenticationFilter;
import com.nfjs.fooddelivery.security.JwtAuthorizationFilter;
import com.nfjs.fooddelivery.security.UserDetailsServiceImpl;
import com.nfjs.fooddelivery.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

  private final JwtUtil jwtUtil;
  private final UserDetailsServiceImpl userDetailsService;
  private final AuthenticationConfiguration authenticationConfiguration;
  private final AuthService authService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil, AuthService authService) throws Exception {
    JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil, authService);
    filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));

    return filter;
  }

  @Bean
  public JwtAuthorizationFilter jwtAuthorizationFilter() {
    return new JwtAuthorizationFilter(userDetailsService, jwtUtil);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);

    http.sessionManagement((sessionManagement) ->
        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    );

    http.authorizeHttpRequests(auth -> auth
        .requestMatchers(
            "/api/auth/signup",
            "/api/auth/signin",
            "/",            // 메인 페이지
            "/error"        // 에러 페이지
        ).permitAll()

        .requestMatchers(HttpMethod.POST, "/api/shops").hasAnyRole("MANAGER", "MASTER")
        .requestMatchers(HttpMethod.GET, "/api/shops/*").hasAnyRole("CUSTOMER", "OWNER", "MANAGER", "MASTER")
        .requestMatchers(HttpMethod.PUT,"/api/shops/{shopId}").hasAnyRole("MANAGER", "MASTER")
        .requestMatchers(HttpMethod.PATCH,"/api/shops/{shopId}").hasAnyRole("MANAGER", "MASTER")

        .requestMatchers(HttpMethod.POST,"/api/shops/{shopId}/menus").hasAnyRole("OWNER","MANAGER", "MASTER")
        .requestMatchers("/api/menus/{menuId}").hasAnyRole("OWNER","MANAGER", "MASTER")
        .requestMatchers("/api/shops/{shopId}/menus/**").hasAnyRole("OWNER","MANAGER", "MASTER")

        .requestMatchers("/api/shops/{shopId}/orders").hasAnyRole("CUSTOMER")
        .requestMatchers("/api/orders/{orderId}/*").hasAnyRole("CUSTOMER", "OWNER", "MANAGER", "MASTER")
        .requestMatchers(HttpMethod.GET,"/api/orders").hasAnyRole("CUSTOMER", "MANAGER", "MASTER")

        .requestMatchers(HttpMethod.POST, "/api/addresses").hasAnyRole("CUSTOMER")

        .requestMatchers(HttpMethod.POST, "/api/categories").hasAnyRole("MANAGER, MASTER")

        .requestMatchers(HttpMethod.POST, "/api/reviews/**").hasAnyRole("CUSTOMER")
        .requestMatchers(HttpMethod.PATCH, "/api/reviews/{reviewId}").hasAnyRole("CUSTOMER", "MANAGER", "MASTER")
        .requestMatchers(HttpMethod.GET, "/api/reviews/{reviewId}").hasAnyRole("CUSTOMER", "OWNER","MANAGER", "MASTER")

        .requestMatchers("/api/ai/**").hasAnyRole("OWNER", "MANAGER", "MASTER")

        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .anyRequest().authenticated()
    );

    http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    http.addFilterAfter(jwtAuthenticationFilter(jwtUtil, authService), JwtAuthorizationFilter.class);

    return http.build();
  }
}
