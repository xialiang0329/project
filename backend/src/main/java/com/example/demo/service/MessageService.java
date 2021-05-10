package com.example.demo.service;


import com.example.demo.pojo.Message;

import java.util.List;

/**
 * (Message)表服务接口
 *
 * @author makejava
 * @since 2021-04-25 11:40:20
 */
public interface MessageService
{

    /**
     * 通过ID查询单条数据
     *
     * @param pkid 主键
     * @return 实例对象
     */
    Message queryById(String pkid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Message> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param pkid 主键
     * @return 是否成功
     */
    boolean deleteById(String pkid);

}
