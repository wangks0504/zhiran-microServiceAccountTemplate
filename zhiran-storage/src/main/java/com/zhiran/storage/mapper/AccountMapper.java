package com.zhiran.storage.mapper;

import com.zhiran.modules.po.AccountPO;

public interface AccountMapper {
    /**
     * 增
     */
    int insert(AccountPO insertAccountPO);
    int insertSelective(AccountPO insertAccountPOSelective);
    /**
     * 删
     */
    int deleteByPrimaryKey(String ID);
    /**
     * 改
     */
    int updateByPrimaryKey(AccountPO updateAccountPO);
    int updateByPrimaryKeySelective(AccountPO updateAccountPOSelective);
    /**
     * 查
     */
    AccountPO checkLogin(AccountPO loginAccountPO);
    AccountPO selectByPrimaryId(String id);

}
