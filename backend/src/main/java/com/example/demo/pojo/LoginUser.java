package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class LoginUser
{

    private String username;

    private String password;

    private String newPassword;

}
