package com.example.demo.controller;

import com.example.demo.pojo.Message;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Message selectOne(String id)
    {
        return null;
    }

    public static void main(String[] args)
    {
        List<Integer> list = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10});
        for (int i = 0; i < list.size(); i++)
        {
            if (i == 3 || i == 5) {
                continue;
            }
            System.out.println(i);
        }
    }

}
