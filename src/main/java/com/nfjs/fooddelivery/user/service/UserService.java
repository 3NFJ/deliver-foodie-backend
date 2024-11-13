package com.nfjs.fooddelivery.user.service;

import com.nfjs.fooddelivery.user.dto.SignupRequestDto;
import com.nfjs.fooddelivery.user.dto.SignupResponseDto;

public interface UserService {

  SignupResponseDto signupUser(SignupRequestDto request);

}
