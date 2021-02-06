package com.example.demo.pojo;

import java.io.Serializable;

/**
 * (Account)实体类
 *
 * @author makejava
 * @since 2021-02-03 15:20:12
 */
public class Account implements Serializable
{
    private static final long serialVersionUID = 989238860835793520L;

    private String pkid;

    private String accountName;

    private String password;

    private String createTime;

    private String userid;

    public String getPkid()
    {
        return pkid;
    }

    public void setPkid(String pkid)
    {
        this.pkid = pkid;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getUserid()
    {
        return userid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid;
    }

}
