package com.nfjs.fooddelivery.user.service;

import com.nfjs.fooddelivery.common.excetpion.ErrorCode;
import com.nfjs.fooddelivery.common.excetpion.UserException;
import com.nfjs.fooddelivery.user.dto.SignupRequestDto;
import com.nfjs.fooddelivery.user.dto.SignupResponseDto;
import com.nfjs.fooddelivery.user.dto.UpdateUserRequestDto;
import com.nfjs.fooddelivery.user.dto.UpdateUserResponseDto;
import com.nfjs.fooddelivery.user.dto.UserResponseDto;
import com.nfjs.fooddelivery.user.entity.User;
import com.nfjs.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public UpdateUserResponseDto updateUser(Long userId, UpdateUserRequestDto request) {

    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

    String encodedPassword = null;
    if (request.getPassword() != null && request.getPassword().isEmpty()) {
      encodedPassword = passwordEncoder.encode(request.getPassword());
    }

    user.update(request, encodedPassword);
    return UpdateUserResponseDto.fromEntity(user);
  }

  public UserResponseDto getUserById(Long userId) {
    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

    return UserResponseDto.fromEntity(user);
  }

  @Transactional
  public void deleteUser(Long userId, String deletedBy) {
    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

    user.delete(deletedBy);
  }
}
