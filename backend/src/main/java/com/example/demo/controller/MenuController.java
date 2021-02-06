package com.example.demo.controller;

import com.example.demo.pojo.Menu;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (Menu)表控制层
 *
 * @author makejava
 * @since 2021-02-03 15:20:22
 */
@RestController
@RequestMapping("menu")
public class MenuController
{
    /**
     * 服务对象
     */
    @Autowired
    private MenuService menuService;


    @GetMapping("pc/queryMenuList")
    public List<Menu> queryPCMenuList()
    {
        return menuService.queryPCMenuList();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Menu selectOne(String id)
    {
        return menuService.queryById(id);
    }




}