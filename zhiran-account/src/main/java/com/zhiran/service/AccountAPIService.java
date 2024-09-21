package com.zhiran.service;

import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.zhiran.common.domin.vo.response.ResponseVO;
import com.zhiran.modules.po.AccountPO;
import com.zhiran.modules.po.AccountPOOriginal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "zhiran-storage-server",path = "/storage/account",contextId = "account")
public interface AccountAPIService {
    /**
     * @param accountPO
     * @return AccountPO
     * 账户注册
     */
    @RequestMapping("/register")
    public AccountPO register(@RequestBody AccountPO accountPO);
    @RequestMapping("/registerOriginal")
    public ResponseVO registerOriginal(@RequestBody AccountPOOriginal accountPOOriginal);
    /**
     * @param accountPO
     * 账户更新
     */
    @RequestMapping("update")
    public void update(@RequestBody AccountPO accountPO);
    @RequestMapping("updateOriginal")
    public void updateOriginal(@RequestBody AccountPOOriginal accountPOOriginal);

    /**
     * @param accountPO
     * @return account
     * 检查登录
     */
    @RequestMapping("checkLogin")
    public AccountPO checkLogin(@RequestBody AccountPO accountPO);

    /**
     * 根据id查询
     * @param id
     * @return accountPO
     */
    @RequestMapping("getAccountById/{id}")
    public AccountPO getAccountById(@PathVariable("id") String id);


}
