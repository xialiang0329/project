package com.example.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2021-02-03 15:20:21
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Menu implements Serializable
{
    private static final long serialVersionUID = 443069342306904072L;

    private String pkid;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父级菜单
     */
    @JSONField(name = "parent_id")
    private String parentId;

    /**
     * 菜单排序
     */
    private String sort;

    /**
     * 菜单图标
     */
    private String icon;

    private String url;

    private String type;

    @JSONField(name = "create_time")
    private String createTime;


    private List<Menu> children;
}
