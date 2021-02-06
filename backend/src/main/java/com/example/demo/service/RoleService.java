package com.example.demo.service;

import com.example.demo.dao.interfaces.RoleDao;
import com.example.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2021-02-03 15:20:24
 */
@Service("roleService")
public class RoleService 
{
    @Autowired
    private RoleDao roleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param pkid 主键
     * @return 实例对象
     */
    public Role queryById(String pkid)
    {
        return roleDao.queryById(pkid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<Role> queryAllByLimit(int offset, int limit)
    {
        return roleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    public Role insert(Role role)
    {
        roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    public Role update(Role role)
    {
        roleDao.update(role);
        return queryById(role.getPkid());
    }

    /**
     * 通过主键删除数据
     *
     * @param pkid 主键
     * @return 是否成功
     */
    public boolean deleteById(String pkid)
    {
        return roleDao.deleteById(pkid) > 0;
    }
}
