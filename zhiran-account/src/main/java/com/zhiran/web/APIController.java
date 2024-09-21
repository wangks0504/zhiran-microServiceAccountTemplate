package com.zhiran.web;

import com.zhiran.common.constant.ZhiranConstants;
import com.zhiran.common.domin.vo.response.ResponseVO;
import com.zhiran.common.group.Group;
import com.zhiran.common.initial.annotation.RequestInitial;
import com.zhiran.handler.AccountHandler;
import com.zhiran.modules.po.AccountPO;
import com.zhiran.modules.vo.AccountVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
@Api(value = "账户操作Controller",tags = {"账户管理"})
@ApiResponses(@ApiResponse(code = 200 ,message = "已经处理成功"))
public class APIController {
    @Autowired
    private AccountHandler accountHandler;
    @PostMapping("/register")
    @ApiOperation(value = "注册接口",tags = {"账户管理"})
    @RequestInitial(group = Group.Creat.class)
    public ResponseVO<AccountPO> register(@Validated(value = Group.Creat.class) @RequestBody AccountVO accountVO) throws NoSuchAlgorithmException {
            return accountHandler.register(accountVO);
    }
    @PostMapping("/modifyPassword")
    @ApiOperation(value = "更新密码接口",tags = "账户管理")
    @RequestInitial(group = Group.Update.class)
    public ResponseVO<AccountPO> modifyPassword(@Validated(value = Group.Update.class)@RequestBody AccountVO accountVO) throws NoSuchAlgorithmException {
        return accountHandler.modifyPassword(accountVO);
    }
    @ApiOperation(value = "用户登录接口", tags = {"账户管理"})
    @PostMapping("/login")
    public ResponseVO<AccountVO> login(@Validated(Group.Select.class) @RequestBody AccountVO accountVO) throws NoSuchAlgorithmException {
        return accountHandler.accountToken(accountVO);
    }
    @ApiOperation(value = "验证Token", tags = {"账户管理"})
    @PostMapping("/verifyToken")
    public ResponseVO verifyToken(@RequestHeader(ZhiranConstants.SESSION_TOKEN_KEY) String sessionID) {
        return accountHandler.verifyToken(sessionID);
    }
    @GetMapping("test")
    public String test(){
        return  "测试成功";
    }
}
