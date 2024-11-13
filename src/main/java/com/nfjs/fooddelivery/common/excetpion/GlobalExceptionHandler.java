package com.nfjs.fooddelivery.common.excetpion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgument(
      IllegalArgumentException e
  ) {
    return ResponseEntity.badRequest()
        .body(new ErrorResponse(e.getMessage()));
  }

  @ExceptionHandler(ShopException.class)
  public ResponseEntity<ErrorResponse> handleShopException(ShopException e) {

    return ResponseEntity.status(e.getErrorCode().getStatus()).body(new ErrorResponse(e.getMessage()));
  }
}
