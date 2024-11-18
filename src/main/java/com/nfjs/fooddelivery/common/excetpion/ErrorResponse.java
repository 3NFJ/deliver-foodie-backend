package com.nfjs.fooddelivery.common.excetpion;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
  private final LocalDateTime timestamp;
  private final int status;
  private final String message;
  private Map<String, String> errors;

  public ErrorResponse(int status, String message) {
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.message = message;
  }

  public ErrorResponse(int status, String message, Map<String, String> errors) {
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }
}
