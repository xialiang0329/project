package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.util.EntWeChatUtil;
import com.example.demo.common.util.ImageCodeUtil;
import com.example.demo.common.util.vo.wechat.GetUserInfo;
import com.example.demo.pojo.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * (Account)表控制层
 *
 * @author makejava
 * @since 2021-02-03 15:20:19
 */
@RestController
@RequestMapping("account")
public  class  AccountController
{

    @Autowired
    private AccountService accountService;

    @Autowired
    private EntWeChatUtil entWeChatUtil;

    /**
     * 获取图形验证码
     */
    @GetMapping("getImageCode")
    public  String getImageCode(HttpServletRequest request,HttpServletResponse response)
    {

        return ImageCodeUtil.getRandCode(request);
    }
    /**
     * 图形验证码效验
     */
    @GetMapping("verityImageCode")
    public  Boolean  verityImageCode(HttpServletRequest request,HttpServletResponse response,@RequestParam("imageCode")String imageCode)
    {
        if (ImageCodeUtil.verityImageCode(imageCode,request)) {
            return true;
        }
        throw new RuntimeException("验证码错误!");
    }

    /**
     * 用户登录
     * @param account
     */
    @PostMapping("login")
    public Account login(@RequestBody Account account){
        return accountService.queryByAccountNameAndPassword(account.getAccountName(), account.getPassword());
    }


    /**
     * 路由跳转至移动端
     * @param request
     * @param response
     */
    @GetMapping("redirect_uri")
    public void redirectUri(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        response.sendRedirect("http://activate.navicat.com:3001/#/");
    }

    /**
     * 接收扫码code
     * @param request
     * @param response
     */
    @GetMapping("getQrCode")
    public GetUserInfo getQrCode(HttpServletRequest request, HttpServletResponse response,@RequestParam("code") String code){
        GetUserInfo userId = entWeChatUtil.getUserId(code);
        GetUserInfo userInfo = entWeChatUtil.getUserInfo(userId.getUserId());
        return userInfo;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Account selectOne(String id)
    {
        return accountService.queryById(id);
    }
    
    
    

}
