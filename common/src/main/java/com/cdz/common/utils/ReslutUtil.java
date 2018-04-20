package com.cdz.common.utils;

import com.cdz.common.enums.ReslutEnum;
import com.cdz.common.vo.ResultVo;

/**
 * @author chendezhi
 * @date 2018/4/20 10:58
 */
public class ReslutUtil<T> {

    public static<T> ResultVo success(T data) {
        return new ResultVo<>(ReslutEnum.SUCCESS, data);
    }

    public static ResultVo error(String msg){
        return new ResultVo(msg,ReslutEnum.ERROR.getCode());
    }

}
