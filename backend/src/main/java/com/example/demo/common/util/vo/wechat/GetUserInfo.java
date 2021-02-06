package com.example.demo.common.util.vo.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class GetUserInfo {
    private String errcode;// 0  返回码

    private String errmsg;// ok  对返回码的文本描述内容

    private String UserId;//成员UserID。若需要获得用户详情信息，可调用通讯录接口：读取成员

    private String OpenId;//非企业成员的标识，对当前企业唯一

    private String mobile;//

    private String open_userid;

    private String qrCode;
}
