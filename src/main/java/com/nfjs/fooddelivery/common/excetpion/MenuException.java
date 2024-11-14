package com.nfjs.fooddelivery.common.excetpion;

import lombok.Getter;

@Getter
public class MenuException extends RuntimeException{
    private final ErrorCode errorCode;

    public MenuException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
