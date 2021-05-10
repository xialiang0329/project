package com.example.demo.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Account)实体类
 *
 * @author makejava
 * @since 2021-02-03 15:20:12
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Account implements Serializable {
    private static final long serialVersionUID = 989238860835793520L;

    private String pkid;

    private String accountName;

    private String password;

    private String createTime;

    private String userId;

    private String status;

    private String token;//标识


}
