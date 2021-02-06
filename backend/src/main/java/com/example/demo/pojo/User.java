package com.example.demo.pojo;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-02-03 15:20:25
 */
public class User implements Serializable
{
    private static final long serialVersionUID = 577042502561526498L;

    private String pkid;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户出生
     */
    private String birthday;

    /**
     * 性别(1.男  2.女)
     */
    private String sex;

    /**
     * 身份证号
     */
    private String cardid;

    /**
     * 户籍所在地
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 账号
     */
    private String accountid;

    public String getPkid()
    {
        return pkid;
    }

    public void setPkid(String pkid)
    {
        this.pkid = pkid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getCardid()
    {
        return cardid;
    }

    public void setCardid(String cardid)
    {
        this.cardid = cardid;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAccountid()
    {
        return accountid;
    }

    public void setAccountid(String accountid)
    {
        this.accountid = accountid;
    }

}
