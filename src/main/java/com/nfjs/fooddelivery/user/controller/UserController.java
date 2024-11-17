package com.nfjs.fooddelivery.user.controller;


import com.nfjs.fooddelivery.user.dto.UpdateUserRequestDto;
import com.nfjs.fooddelivery.user.dto.UpdateUserResponseDto;
import com.nfjs.fooddelivery.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;


  @PutMapping("/{userId}")
  ResponseEntity<UpdateUserResponseDto> updateUser(
      @PathVariable Long userId,
      @Valid @RequestBody UpdateUserRequestDto request
  ) {
    UpdateUserResponseDto response = userService.updateUser(userId, request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
