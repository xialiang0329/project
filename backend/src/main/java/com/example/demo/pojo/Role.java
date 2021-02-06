package com.example.demo.pojo;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2021-02-03 15:20:24
 */
public class Role implements Serializable
{
    private static final long serialVersionUID = -37462765554164049L;

    private String pkid;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 创建时间
     */
    private String createTime;

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

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

}
