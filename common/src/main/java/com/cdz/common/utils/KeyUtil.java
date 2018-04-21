package com.cdz.common.utils;

import com.cdz.common.dto.OrderDTO;

import java.util.Random;

/**
 * @author chendezhi
 * @date 2018/4/20 15:11
 */
public class KeyUtil {

    public static synchronized String getUniqueKey(){

        Random random = new Random();

        Integer number = random.nextInt(900_000)+10000;

        return number.toString();
    }
}
