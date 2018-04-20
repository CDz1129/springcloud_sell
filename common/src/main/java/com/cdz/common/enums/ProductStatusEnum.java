package com.cdz.common.enums;

import lombok.Getter;

/**
 * @author chendezhi
 * @date 2018/4/20 11:13
 * 商品状态
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"正常"),
    down(1,"下架");

    private int code;
    private String msg;

    ProductStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

