package com.example.demo.service;

import com.example.demo.common.code.StatusCodeEnum;
import com.example.demo.common.util.TokenUtil;
import com.example.demo.dao.interfaces.AccountDao;
import com.example.demo.dao.interfaces.UserDao;
import com.example.demo.pojo.Account;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.Token;

import java.util.List;

/**
 * (Account)表服务实现类
 *
 * @author makejava
 * @since 2021-02-03 15:20:18
 */
@Service
public class AccountService
{
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;


    /**
     * 通过账号与密码
     *
     * @param accountName 账号
     * @param password 密码
     * @return 实例对象
     */
    public User queryByAccountNameAndPassword(String accountName,String password)
    {
        Account accountRsp = accountDao.selectByAccountNameAndPassword(accountName,password);
        if (null != accountRsp) {
            //登录成功 修改账号状态
            accountRsp.setStatus(StatusCodeEnum.loginStatus.on_line.getKey());
            accountRsp.setPassword(null);
            accountDao.update(accountRsp);
            User user = userDao.queryById(accountRsp.getUserId());
            user.setAccount(accountRsp);
            return user;
        }
        throw new RuntimeException("用户名或密码错误!");
    }



    /**
     * 通过ID查询单条数据
     *
     * @param pkid 主键
     * @return 实例对象
     */
    public Account queryById(String pkid)
    {
        return accountDao.queryById(pkid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<Account> queryAllByLimit(int offset, int limit)
    {
        return accountDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    public Account insert(Account account)
    {
        accountDao.insert(account);
        return account;
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    public Account update(Account account)
    {
        accountDao.update(account);
        return queryById(account.getPkid());
    }

    /**
     * 通过主键删除数据
     *
     * @param pkid 主键
     * @return 是否成功
     */
    public boolean deleteById(String pkid)
    {
        return accountDao.deleteById(pkid) > 0;
    }
}
