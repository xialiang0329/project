package com.example.demo.pojo;

import java.io.Serializable;

/**
 * (RelationRoleMenu)实体类
 *
 * @author makejava
 * @since 2021-02-03 15:20:22
 */
public class RelationRoleMenu implements Serializable
{
    private static final long serialVersionUID = 372419133336069522L;

    private String pkid;

    /**
     * 角色ID
     */
    private String roleid;

    /**
     * 菜单ID
     */
    private String menuid;

    public String getPkid()
    {
        return pkid;
    }

    public void setPkid(String pkid)
    {
        this.pkid = pkid;
    }

    public String getRoleid()
    {
        return roleid;
    }

    public void setRoleid(String roleid)
    {
        this.roleid = roleid;
    }

    public String getMenuid()
    {
        return menuid;
    }

    public void setMenuid(String menuid)
    {
        this.menuid = menuid;
    }

}
