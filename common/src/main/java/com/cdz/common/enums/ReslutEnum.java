package com.cdz.common.enums;

import lombok.Getter;

/**
 * @author chendezhi
 * @date 2018/4/20 10:59
 */
@Getter
public enum  ReslutEnum {
    SUCCESS(0,"成功"),
    ERROR(500,"错误")
            ;

    private int code;

    private String msg;

    ReslutEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
