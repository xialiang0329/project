package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.util.WeChatOfficialAccountUtil;
import com.example.demo.common.util.vo.officialaccount.OfficialAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("postmain")
public class PostManTest
{

    @Autowired
    private WeChatOfficialAccountUtil officialAccountUtil;

    @GetMapping("/createWeChatOfficialAccountAccessToken")
    public String createWeChatOfficialAccountAccessToken(){
        return officialAccountUtil.queryAccessToken();
    }
}
