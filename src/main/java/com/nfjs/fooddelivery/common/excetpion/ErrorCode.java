package com.nfjs.fooddelivery.common.excetpion;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  // 일반
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력값입니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."),

  // 회원기능 관련
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
  UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자 입니다"),
  INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
  EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),

  // 가게
  INVALID_SHOP_NAME(HttpStatus.BAD_REQUEST, "가게 이름은 한글, 영어, 숫자만 포함 가능합니다."),
  DUPLICATE_SHOP_NAME(HttpStatus.CONFLICT, "이미 존재하는 가게명 입니다."),
  SHOP_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "가게에 대한 권한이 없습니다."),
  SHOP_NOT_FOUND(HttpStatus.NOT_FOUND, "가게를 찾을 수 없습니다."),

  // 메뉴
  INVALID_MENU_NAME(HttpStatus.BAD_REQUEST, "메뉴 이름은 한글, 영어, 숫자만 포함 가능합니다."),
  MENU_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "메뉴에 대한 권한이 없습니다."),
  DUPLICATE_MENU_NAME(HttpStatus.CONFLICT, "가게에 이미 존재하는 메뉴명 입니다."),
  SHOP_OWNER_MISMATCH(HttpStatus.CONFLICT, "해당 메뉴 가게의 유저가 일치하지 않습니다."),
  MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다."),
  INVALID_MENU_PRICE(HttpStatus.BAD_REQUEST, "유효하지 않은 메뉴 가격입니다.");

  private final HttpStatus status;
  private final String message;

  ErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

}
