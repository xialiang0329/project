package com.example.demo.controller;

import com.example.demo.pojo.RelationRoleMenu;
import com.example.demo.service.RelationRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (RelationRoleMenu)表控制层
 *
 * @author makejava
 * @since 2021-02-03 15:20:23
 */
@RestController
@RequestMapping("relationRoleMenu")
public class RelationRoleMenuController
{
    /**
     * 服务对象
     */
    @Autowired
    private RelationRoleMenuService relationRoleMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RelationRoleMenu selectOne(String id)
    {
        return relationRoleMenuService.queryById(id);
    }

}
