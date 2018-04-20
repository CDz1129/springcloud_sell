package com.cdz.common.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author chendezhi
 * @date 2018/4/19 18:19
 */
@Data
@Entity
public class UserInfo {
    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
    private byte role;
    private Date createTime;
    private Date updateTime;
}
