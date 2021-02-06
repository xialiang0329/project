package com.example.demo.dao.interfaces;

import com.example.demo.pojo.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Menu)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-03 15:20:21
 */
@Repository
public interface MenuDao
{

    /**
     * 查询pc端查单集合
     * @return 实例对象
     */
    List<Menu> findPCMenuList();

    /**
     * 查询子级集合
     * @return 实例对象
     */
    List<Menu> findPCMenuListByParentId(String parentId);
    /**
     * 通过ID查询单条数据
     *
     * @param pkid 主键
     * @return 实例对象
     */
    Menu queryById(String pkid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Menu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param menu 实例对象
     * @return 对象列表
     */
    List<Menu> queryAll(Menu menu);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int insert(Menu menu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Menu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Menu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Menu> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Menu> entities);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 影响行数
     */
    int update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param pkid 主键
     * @return 影响行数
     */
    int deleteById(String pkid);

}

