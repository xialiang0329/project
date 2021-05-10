package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-02-03 15:20:25
 */
@RestController
@RequestMapping("user")
public class UserController
{
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(String id)
    {
        return userService.queryById(id);
    }

    @GetMapping("search-contacts")
    public List<User> searchUserRelationContactsByUserId(@RequestParam("userId") String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new RuntimeException("参数异常!");
        }
        return userService.queryUserRelationContactsByUserId(userId);
    }

}
