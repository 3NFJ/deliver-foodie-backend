package com.nfjs.fooddelivery.security;

import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

    return new UserDetailsImpl(user);
  }

  public UserDetails loadUserByUserNumber(UUID userNumber) {
    User user = userRepository.findByUserNumber(userNumber)
        .orElseThrow(() -> new UsernameNotFoundException("사용자 번호를 찾을 수 없습니다."));
    return new UserDetailsImpl(user);
  }
}
