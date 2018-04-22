package com.cdz.common.urlparam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author chendezhi
 * @date 2018/4/20 13:42
 */
@Data
public class OrderFrom {

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为null")
    private String name;

    /**
     * 用户手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    /**
     * 用户地址
     */
    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "openid不能为空")
    private String openid;

    /**
     * 购物项
     */
    @Size(message = "购物项不能为空")
    private List<OrderItem> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem {

        /**
         * 商品id
         */
        private String productId;

        /**
         * 商品数量
         */
        private int productQuantity;


    }

}
