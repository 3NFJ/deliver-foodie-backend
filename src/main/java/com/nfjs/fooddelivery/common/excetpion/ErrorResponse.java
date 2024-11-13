package com.nfjs.fooddelivery.common.excetpion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
  private final String message;
}
