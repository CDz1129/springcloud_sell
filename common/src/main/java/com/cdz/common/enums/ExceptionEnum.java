package com.cdz.common.enums;

import lombok.Getter;

/**
 * @author CDz_
 * @create 2018-04-21 15:29
 * 异常信息 枚举
 **/
@Getter
public enum  ExceptionEnum {

    PRODUCT_NOT_EXIST(500,"商品不存在"),
    PRODUCT_NO_STOCK(500,"商品库存不足");


    private int code;

    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
