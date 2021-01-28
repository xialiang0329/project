package com.example.demo.controller;

import com.example.demo.pojo.LoginUser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("loginUser")
public class Login
{

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
    public String getQrCode(HttpServletRequest request, HttpServletResponse response,@RequestParam("code") String code){
        return "sb";
    }



    /**
     * 用户登录
     * @param loginUser
     */
    @PostMapping("login")
    public void login(LoginUser loginUser){

    }

    /**
     * 用户注册
     * @param loginUser
     */
    @PostMapping("register")
    public void register(LoginUser loginUser){

    }

}
