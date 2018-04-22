package com.cdz.common.enums;

import lombok.Getter;

/**
 * @author CDz_
 * @create 2018-04-21 15:53
 * 支付状态
 **/
@Getter
public enum PayStatusEnum {

    WAIT(0,"未支付"),
    PAY(1,"已支付"),
    PAY_ERORR(-1,"支付失败"),;

    private int code;
    private String msg;

    PayStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
