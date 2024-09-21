package com.zhiran.storage.service;

import com.zhiran.common.domin.vo.response.ResponseVO;
import com.zhiran.modules.po.AccountPO;
import com.zhiran.modules.po.AccountPOOriginal;
import com.zhiran.storage.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/account")
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @RequestMapping("/register")
    public AccountPO register(@RequestBody AccountPO accountPO){
        accountMapper.insert(accountPO);
        return accountPO;
    }
    @RequestMapping("/registerOriginal")
    public ResponseVO registerOriginal(@RequestBody AccountPOOriginal accountPOOriginal){
        accountMapper.insertOriginal(accountPOOriginal);
        return ResponseVO.success("666","OK");
    }

    @RequestMapping("/update")
    public void update(@RequestBody AccountPO record){
        accountMapper.updateByPrimaryKeySelective(record);
    }
    @RequestMapping("/updateOriginal")
    public void updateOringianl(@RequestBody AccountPOOriginal accountPOOriginal){
        accountMapper.updateByPrimaryKeyOriginal(accountPOOriginal);
    }
    @RequestMapping("/checkLogin")
    public AccountPO checkLogin(@RequestBody AccountPO accountPO){
         return accountMapper.checkLogin(accountPO);
    }
    @RequestMapping("/getAccountById/{id}")
    public AccountPO gerAccountById(@PathVariable("id") String id){
        return accountMapper.selectByPrimaryId(id);
    }
}
