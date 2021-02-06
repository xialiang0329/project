package com.example.demo.service;

import com.example.demo.dao.interfaces.MenuDao;
import com.example.demo.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * (Menu)表服务实现类
 *
 * @author makejava
 * @since 2021-02-03 15:20:22
 */
@Service("menuService")
public class MenuService
{
    @Autowired
    private MenuDao menuDao;

    /**
     * 查询pc端菜单集合
     * @return 实例对象
     */
    public List<Menu> queryPCMenuList()
    {
        List<Menu> pcMenuList = menuDao.findPCMenuList();
        pcMenuList.forEach(menu ->{
            menu.setChildren(getMenuList(menu.getPkid()));
        });
        return pcMenuList;
    }

    private List<Menu> getMenuList(String pkid) {
        List<Menu> menus =menuDao.findPCMenuListByParentId(pkid);
        menus.forEach(menu ->{
            menu.setChildren(getMenuList(menu.getPkid()));
        });
        return menus;
    }




    /**
     * 通过ID查询单条数据
     *
     * @param pkid 主键
     * @return 实例对象
     */
    public Menu queryById(String pkid)
    {
        return menuDao.queryById(pkid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<Menu> queryAllByLimit(int offset, int limit)
    {
        return menuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    public Menu saveMenu(Menu menu)
    {
        String pkid = UUID.randomUUID().toString();
        menu.setPkid(pkid);
        menuDao.insert(menu);
        return menu;
    }

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    public Menu update(Menu menu)
    {
        menuDao.update(menu);
        return queryById(menu.getPkid());
    }

    /**
     * 通过主键删除数据
     *
     * @param pkid 主键
     * @return 是否成功
     */
    public boolean deleteById(String pkid)
    {
        return menuDao.deleteById(pkid) > 0;
    }
}
