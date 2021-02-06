package com.example.demo.service;

import com.example.demo.dao.interfaces.UserDao;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-02-03 15:20:25
 */
@Service("userService")
public class UserService 
{
    @Autowired
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param pkid 主键
     * @return 实例对象
     */
    public User queryById(String pkid)
    {
        return userDao.queryById(pkid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<User> queryAllByLimit(int offset, int limit)
    {
        return userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public User insert(User user)
    {
        userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public User update(User user)
    {
        userDao.update(user);
        return queryById(user.getPkid());
    }

    /**
     * 通过主键删除数据
     *
     * @param pkid 主键
     * @return 是否成功
     */
    public boolean deleteById(String pkid)
    {
        return userDao.deleteById(pkid) > 0;
    }
}
