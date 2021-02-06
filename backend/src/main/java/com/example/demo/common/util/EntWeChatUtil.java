package com.example.demo.common.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.util.vo.wechat.GetUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 企业微信工具类
 */
@Service
public class EntWeChatUtil
{

    @Value("${AppId}")
    private String appId;
    @Value("${AgentId}")
    private String agentId;
    @Value("${Secret}")
    private String secret;
    @Value("${access_token}")
    private String accessToken;

    private  RestTemplate restTemplate =  new RestTemplate();

    private  final String getUserId = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";

    private  final String getUserInfo = "https://qyapi.weixin.qq.com/cgi-bin/user/get";

    public  GetUserInfo getUserId(String code){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getUserId)
            .queryParam("access_token", accessToken)
            .queryParam("code", code);
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<JSONObject>
            response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, JSONObject.class);
        return JSONObject.toJavaObject(response.getBody(), GetUserInfo.class);
    }
    public  GetUserInfo getUserInfo(String userid){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getUserInfo)
            .queryParam("access_token", accessToken)
            .queryParam("userid", userid);
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<JSONObject>
            response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, JSONObject.class);
        return JSONObject.toJavaObject(response.getBody(), GetUserInfo.class);
    }

}
