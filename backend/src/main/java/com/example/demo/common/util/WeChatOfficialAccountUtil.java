package com.example.demo.common.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.code.StatusCodeEnum;
import com.example.demo.common.util.vo.officialaccount.OfficialAccount;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class WeChatOfficialAccountUtil
{

    @Value("${weChat_Official_Account_Appid}")
    private String appId;
    @Value("${weChat_Official_Account_Secret}")
    private String secret;
    @Value("${vue_phone}")
    private String vuePhone;


    @Autowired
    private StringRedisTemplate redisTemplate;

    private RestTemplate restTemplate =  new RestTemplate();

    private final String grantType = "client_credential";

    private final String type = "jsapi";

    private final String createAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";

    private final String queryJsApiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";


    //获取token
    public String queryAccessToken(){
        //获取过期时间  是否过期
        Long expire = redisTemplate.getExpire(StatusCodeEnum.officialAccount.access_token.getKey());
        if (expire != -2) {//未过期
            return redisTemplate.opsForValue().get(StatusCodeEnum.officialAccount.access_token.getKey());
        } else {
            OfficialAccount officialAccount = createAccessToken();
            return officialAccount.getAccess_token();
        }
    }

    //获取jsapi_ticket
    private String queryJsApiTicket(){
        //获取过期时间  是否过期
        Long expire = redisTemplate.getExpire(StatusCodeEnum.officialAccount.access_token.getKey());
        if (expire != -2) {//未过期
            return redisTemplate.opsForValue().get(StatusCodeEnum.officialAccount.ticket.getKey());
        } else {
            OfficialAccount officialAccount = createJsApiTicket();
            return officialAccount.getTicket();
        }
    }

    //获取signature
    private String querySignature(String url){
        Long expire = redisTemplate.getExpire(StatusCodeEnum.officialAccount.access_token.getKey());
        //获取过期时间  是否过期
        if (expire != -2) {//未过期
            return redisTemplate.opsForValue().get(StatusCodeEnum.officialAccount.signature.getKey());
        } else {
            return createSignature(queryJsApiTicket(),url);
        }
    }
    //获取scan config
    public JSONObject queryOfficialAccountConfig(String url){
        JSONObject scanConfig = new JSONObject();
        scanConfig.fluentPut("appId",appId);
        scanConfig.fluentPut("signature",querySignature(url));
        scanConfig.fluentPut("timestamp",redisTemplate.opsForValue().get(StatusCodeEnum.officialAccount.timestamp.getKey()));
        scanConfig.fluentPut("nonceStr",redisTemplate.opsForValue().get(StatusCodeEnum.officialAccount.noncestr.getKey()));
        return scanConfig;
    }

    //创建token (刷新)
    private OfficialAccount createAccessToken(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createAccessTokenUrl)
            .queryParam("grant_type", grantType)
            .queryParam("appid", appId)
            .queryParam("secret", secret);
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<JSONObject>
            response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, JSONObject.class);
        OfficialAccount officialAccount = JSONObject.toJavaObject(response.getBody(), OfficialAccount.class);
        //token 存入redis
        redisTemplate.opsForValue().set(StatusCodeEnum.officialAccount.access_token.getKey(), officialAccount.getAccess_token());
        //设置过期时间
        redisTemplate.expire(StatusCodeEnum.officialAccount.access_token.getKey(),officialAccount.getExpires_in() , TimeUnit.SECONDS);
        return officialAccount;
    }

    //创建 jsapi_ticket
    private OfficialAccount createJsApiTicket(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(queryJsApiTicketUrl)
            .queryParam("access_token", queryAccessToken())
            .queryParam("type", type);
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<JSONObject> response =
            restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, JSONObject.class);
        OfficialAccount officialAccount = JSONObject.toJavaObject(response.getBody(), OfficialAccount.class);
        if (officialAccount.getErrcode() == StatusCodeEnum.SUCCESS_CODE) {
            //ticket 存入redis
            redisTemplate.opsForValue().set(StatusCodeEnum.officialAccount.ticket.getKey(), officialAccount.getTicket());
        }
        return officialAccount;
    }

    //创建签名 noncestr（随机字符串）,有效的jsapi_ticket, timestamp（时间戳）, url（当前网页的URL，不包含#及其后面部分）
    private String createSignature(String jsapiTicket,String href){
        String noncestr = UUID.randomUUID().toString().replaceAll("-","");
        Long timestamp = System.currentTimeMillis() / 1000;

        String signature = "jsapi_ticket="+jsapiTicket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+(href.indexOf("#") >= 0 ? href.substring(0, href.indexOf("#")) : href);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(signature.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        //signature 存入redis
        redisTemplate.opsForValue().set(StatusCodeEnum.officialAccount.signature.getKey(),signature);
        redisTemplate.opsForValue().set(StatusCodeEnum.officialAccount.timestamp.getKey(),String.valueOf(timestamp));
        redisTemplate.opsForValue().set(StatusCodeEnum.officialAccount.noncestr.getKey(),noncestr);
        return signature;
    }

    private  String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
