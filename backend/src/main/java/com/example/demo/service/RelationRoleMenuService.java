package com.example.demo.service;

import com.example.demo.dao.interfaces.RelationRoleMenuDao;
import com.example.demo.pojo.RelationRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (RelationRoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2021-02-03 15:20:23
 */
@Service("relationRoleMenuService")
public class RelationRoleMenuService
{
    @Autowired
    private RelationRoleMenuDao relationRoleMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param pkid 主键
     * @return 实例对象
     */
    public RelationRoleMenu queryById(String pkid)
    {
        return relationRoleMenuDao.queryById(pkid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<RelationRoleMenu> queryAllByLimit(int offset, int limit)
    {
        return relationRoleMenuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param relationRoleMenu 实例对象
     * @return 实例对象
     */
    public RelationRoleMenu insert(RelationRoleMenu relationRoleMenu)
    {
        relationRoleMenuDao.insert(relationRoleMenu);
        return relationRoleMenu;
    }

    /**
     * 修改数据
     *
     * @param relationRoleMenu 实例对象
     * @return 实例对象
     */
    public RelationRoleMenu update(RelationRoleMenu relationRoleMenu)
    {
        relationRoleMenuDao.update(relationRoleMenu);
        return queryById(relationRoleMenu.getPkid());
    }

    /**
     * 通过主键删除数据
     *
     * @param pkid 主键
     * @return 是否成功
     */
    public boolean deleteById(String pkid)
    {
        return relationRoleMenuDao.deleteById(pkid) > 0;
    }
}
