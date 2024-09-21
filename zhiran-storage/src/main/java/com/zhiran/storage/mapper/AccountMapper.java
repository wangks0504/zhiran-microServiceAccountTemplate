package com.zhiran.storage.mapper;

import com.zhiran.modules.po.AccountPO;
import com.zhiran.modules.po.AccountPOOriginal;

public interface AccountMapper {
    /**
     * 增
     */
    int insert(AccountPO insertAccountPO);
    int insertOriginal(AccountPOOriginal accountPOOriginal);
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
    int updateByPrimaryKeyOriginal(AccountPOOriginal updateAccountPOOriginal);
    /**
     * 查
     */
    AccountPO checkLogin(AccountPO loginAccountPO);
    AccountPO selectByPrimaryId(String id);

}
