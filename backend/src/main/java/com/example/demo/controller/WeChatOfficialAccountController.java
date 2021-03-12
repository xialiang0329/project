package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.util.WeChatOfficialAccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("officialAccount")
public class WeChatOfficialAccountController
{

    @Autowired
    private WeChatOfficialAccountUtil officialAccountUtil;

    @GetMapping("/config")
    public JSONObject queryRedisOfficialAccountConfig(@RequestParam("url") String url){
        return officialAccountUtil.queryOfficialAccountConfig(url);
    }

}
