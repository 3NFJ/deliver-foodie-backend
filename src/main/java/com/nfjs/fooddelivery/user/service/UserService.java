package com.nfjs.fooddelivery.user.service;

import com.nfjs.fooddelivery.user.dto.SignupRequestDto;
import com.nfjs.fooddelivery.user.dto.SignupResponseDto;
import com.nfjs.fooddelivery.user.dto.UpdateUserRequestDto;
import com.nfjs.fooddelivery.user.dto.UpdateUserResponseDto;
import com.nfjs.fooddelivery.user.dto.UserResponseDto;

public interface UserService {

  SignupResponseDto signupUser(SignupRequestDto request);
  UpdateUserResponseDto updateUser(Long userId, UpdateUserRequestDto request);
  UserResponseDto getUserById(Long userId);
  void deleteUser(Long userId, String deletedBy);
}
