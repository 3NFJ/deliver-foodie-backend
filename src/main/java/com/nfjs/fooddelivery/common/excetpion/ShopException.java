package com.nfjs.fooddelivery.common.excetpion;

import lombok.Getter;

@Getter
public class ShopException extends RuntimeException{
    private final ErrorCode errorCode;

    public ShopException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
