package com.example.demo.controller;

import com.example.demo.pojo.Message;
import com.example.demo.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2021-04-25 11:39:33
 */
@RestController
@RequestMapping("message")
public class MessageController
{
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Message selectOne(String id)
    {
        return this.messageService.queryById(id);
    }

}
