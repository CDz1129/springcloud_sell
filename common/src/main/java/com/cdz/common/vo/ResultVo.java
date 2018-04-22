package com.cdz.common.vo;

import com.cdz.common.enums.ReslutEnum;
import lombok.Data;

/**
 * @author chendezhi
 * @date 2018/4/20 10:23
 */
@Data
public class ResultVo<T> {

    private String msg;

    private T data;

    private int code;

    public ResultVo(T data, int code) {
        this.data = data;
        this.code = code;
    }



    public ResultVo(ReslutEnum reslutEnum) {
        this.msg = reslutEnum.getMsg();
        this.code = reslutEnum.getCode();
    }

    public ResultVo(String msg, T data, int code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public ResultVo(ReslutEnum reslutEnum, T data){
        this.code = reslutEnum.getCode();
        this.msg = reslutEnum.getMsg();
        this.data = data;
    }



    public ResultVo() {
    }
}
