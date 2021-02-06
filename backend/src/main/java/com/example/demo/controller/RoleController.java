package com.example.demo.controller;

import com.example.demo.pojo.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (Role)表控制层
 *
 * @author makejava
 * @since 2021-02-03 15:20:24
 */
@RestController
@RequestMapping("role")
public class RoleController
{
    /**
     * 服务对象
     */
    @Autowired
    private RoleService roleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Role selectOne(String id)
    {
        return roleService.queryById(id);
    }

}
