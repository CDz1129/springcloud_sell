package com.cdz.common.enums;

import lombok.Getter;

/**
 * @author CDz_
 * @create 2018-04-21 15:52
 * 订单状态
 **/
@Getter
public enum OrderStatusEnum {

    NEW(0,"新建订单"),
    FINISH(1,"完成"),;

    private int code;
    private String msg;

    OrderStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
