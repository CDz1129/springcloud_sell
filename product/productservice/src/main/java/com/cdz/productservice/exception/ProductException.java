package com.cdz.productservice.exception;

import com.cdz.common.enums.ExceptionEnum;

/**
 * @author CDz_
 * @create 2018-04-21 15:25
 **/
public class ProductException extends RuntimeException {

    private int code;

    public ProductException(int code) {
        this.code = code;
    }

    public ProductException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ProductException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

}
