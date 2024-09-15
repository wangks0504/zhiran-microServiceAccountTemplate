package com.zhiran.web;

import com.zhiran.common.domin.vo.response.ResponseVO;
import com.zhiran.common.group.Group;
import com.zhiran.common.initial.annotation.RequestInitial;
import com.zhiran.modules.vo.AccountVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Api(value = "账户操作Controller",tags = {"账户管理"})
@ApiResponses(@ApiResponse(code = 200 ,message = "已经处理成功"))
public class APIController {
    @PostMapping("/register")
    @ApiOperation(value = "注册接口",tags = {"账户管理"})
    @RequestInitial(group = Group.Creat.class)
    public ResponseVO<AccountVO> register(@Validated(value = Group.Creat.class) @RequestBody AccountVO accountVO){
            return accountHandler.register(accountVO);
    }
}
