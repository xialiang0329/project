package com.example.demo.common.util.vo.officialaccount;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class OfficialAccount
{
    private String access_token;

    private Long expires_in;//过期时间 7200秒

    private String ticket;//临时票据

    private Integer errcode;

    private String errmsg;
}
