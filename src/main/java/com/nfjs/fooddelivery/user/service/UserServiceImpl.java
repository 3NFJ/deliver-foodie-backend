package com.nfjs.fooddelivery.user.service;

import com.nfjs.fooddelivery.user.dto.SignupRequestDto;
import com.nfjs.fooddelivery.user.dto.SignupResponseDto;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public SignupResponseDto signupUser(SignupRequestDto request) {

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("중복된 Email 입니다.");
    }

    return SignupResponseDto.fromEntity(
        userRepository.save(request.toEntity(passwordEncoder)));
  }
}
