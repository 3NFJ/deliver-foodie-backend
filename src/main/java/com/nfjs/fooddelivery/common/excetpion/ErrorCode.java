package com.nfjs.fooddelivery.common.excetpion;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  //일반
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력값입니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."),

  // 회원기능 관련
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

  // 가게
  INVALID_SHOP_NAME(HttpStatus.BAD_REQUEST, "가게 이름은 한글, 영어, 숫자만 포함 가능합니다."),
  DUPLICATE_SHOP_NAME(HttpStatus.CONFLICT, "이미 존재하는 가게명 입니다."),


  // 메뉴
  INVALID_MENU_NAME(HttpStatus.BAD_REQUEST, "메뉴 이름은 한글, 영어, 숫자만 포함 가능합니다."),
  DUPLICATE_MENU_NAME(HttpStatus.CONFLICT, "가게에 이미 존재하는 메뉴명 입니다."),
  MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다."),
  INVALID_MENU_PRICE(HttpStatus.BAD_REQUEST, "유효하지 않은 메뉴 가격입니다.");

  private final HttpStatus status;
  private final String message;

  ErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

}
