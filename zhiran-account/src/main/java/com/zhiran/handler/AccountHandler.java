package com.zhiran.handler;

import com.zhiran.common.domin.vo.response.ResponseVO;
import com.zhiran.common.entity.SessionContext;
import com.zhiran.common.enums.BusinessErrors;
import com.zhiran.common.exception.BusinessRuntimeException;
import com.zhiran.common.helper.RedisSessionHelper;
import com.zhiran.common.initial.annotation.InitialResolver;
import com.zhiran.common.utils.CommonsUtils;
import com.zhiran.common.utils.SnowflakeIdWorker;
import com.zhiran.modules.po.AccountPO;
import com.zhiran.modules.po.AccountPOOriginal;
import com.zhiran.modules.vo.AccountVO;
import com.zhiran.service.AccountAPIService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

import static com.zhiran.common.enums.InitialResolverType.GEN_SNOWFLAKE_ID;

@Component
public class AccountHandler {
    @Autowired
    private AccountAPIService accountAPIService;
    @Autowired
    private RedisSessionHelper redisSessionHelper;

    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(13,14);
    /**
     * @param accountVO
     * @return ResponseVO
     * 注册账户
     */
    public ResponseVO<AccountPO> register(AccountVO accountVO) throws NoSuchAlgorithmException {
        //用户名  密码 电话不能为空
      if(StringUtils.isAnyEmpty(accountVO.getUsername(),accountVO.getPhone(),accountVO.getPhone())){
          throw new  BusinessRuntimeException(BusinessErrors.PARAM_CANNOT_EMPTY);
      }
      //数据库种是否存在
     AccountPO accountPO=  accountAPIService.checkLogin(CommonsUtils.toPO(accountVO));
      if(null != accountPO){
        throw new BusinessRuntimeException(BusinessErrors.DATA_DUPLICATION);
      }
      //生成唯一id
        accountVO.setId(String.valueOf(idWorker.nextId()));
      //保存原始账户数据
        AccountPOOriginal accountPOOriginal = new AccountPOOriginal();
        accountPOOriginal.setUsername(accountVO.getUsername());
        accountPOOriginal.setPassword(accountVO.getPassword());
        accountPOOriginal.setId(accountVO.getId());

        //进行密码相关处理
        String randomSalt = CommonsUtils.getRandomSalt();
        String HashedPassword = CommonsUtils.HashPassword(randomSalt, accountVO.getPassword());

        accountVO.setPasswordSalt(randomSalt);
        accountPOOriginal.setPasswordSalt(accountVO.getPasswordSalt());
        accountVO.setPassword(HashedPassword);
        //写入数据库
        AccountPO result = accountAPIService.register(CommonsUtils.toPO(accountVO));
        ResponseVO responseVO = accountAPIService.registerOriginal(accountPOOriginal);
        return  ResponseVO.success(result);
    }
    /**
     * @param accountVO
     * @return ResponseVO
     * 更改密码
     */
    public ResponseVO modifyPassword (AccountVO accountVO) throws NoSuchAlgorithmException {
        //主要数据不能为空
        if(StringUtils.isAnyEmpty(accountVO.getPhone(),accountVO.getPassword(),accountVO.getId(),accountVO.getNewPassword())){
            return ResponseVO.error("重要信息不能为空");
        }
        //判断新旧密码是否相同
        if(accountVO.getPassword().equals(accountVO.getNewPassword())){
            return  ResponseVO.error("新旧密码不能相同");
        }
        //从数据库中取数据
        AccountPO accountById = accountAPIService.getAccountById(accountVO.getId());
        if(null == accountById){
            return  ResponseVO.error("账户不存在");
        }
        //判断旧密码和新密码是否相等
        String oldPassword = accountVO.getPassword();
        String oldPasswordHashed = CommonsUtils.HashPassword(accountById.getPasswordSalt(), oldPassword);
        if(!accountById.getPassword().equals(oldPasswordHashed) ){
            return ResponseVO.error("旧密码输入错误");
        }
            //执行密码更新操作
            AccountPOOriginal accountPOOriginal = new AccountPOOriginal();
            accountPOOriginal.setPassword(accountVO.getNewPassword());
            accountPOOriginal.setId(accountVO.getId());
            accountPOOriginal.setUsername(accountVO.getUsername());
            accountPOOriginal.setPasswordSalt(accountVO.getPasswordSalt());

           accountVO.setPassword(CommonsUtils.HashPassword(accountById.getPasswordSalt(),accountVO.getNewPassword()));
           accountAPIService.update(CommonsUtils.toPO(accountVO));
           accountAPIService.updateOriginal(accountPOOriginal);
            return ResponseVO.success("null","更改成功");
    }
    /**
     * @param accountVO
     * @return
     * 账户登录
     * 生成Token
     */
    public ResponseVO accountToken(AccountVO accountVO) throws NoSuchAlgorithmException {
        //检查是否登录
        AccountVO vo = verifyAccountLogin(accountVO);
        //添加Token
        SessionContext sessionContext = redisSessionHelper.createSession(vo, vo.getId(), vo.getUsername(), vo.getUseralias(), null);
        vo.setToken(sessionContext.getSessionID());
        return ResponseVO.success(vo);
    }
    /**
     * @param sessionID
     * @
     */
    public ResponseVO verifyToken(String sessionID){
        SessionContext sessionContext = redisSessionHelper.getSession(sessionID);
        if(null == sessionContext){
            throw new BusinessRuntimeException(BusinessErrors.DATA_NOT_EXIST,"Token不存在");
        }
        if(null == sessionContext.getAccountID()){
            throw new BusinessRuntimeException(BusinessErrors.DATA_STATUS_ERROR,"Token已经过期");
        }
        AccountPO accountPOById = accountAPIService.getAccountById(sessionContext.getAccountID());
        if(null == accountPOById){
            throw new BusinessRuntimeException(BusinessErrors.DATA_NOT_EXIST,"Token中账户不存在");
        }
        return  ResponseVO.success(CommonsUtils.toVO(accountPOById));
    }

    /**
     * @param accountVO
     * @return accountPO
     * 验证账户登录
     */
    public AccountVO verifyAccountLogin(AccountVO accountVO) throws NoSuchAlgorithmException {
        //判断用户名或电话是否为空
        if(StringUtils.isAnyEmpty(accountVO.getUsername(),accountVO.getPhone())){
            throw new BusinessRuntimeException(BusinessErrors.PARAM_CANNOT_EMPTY);
        }
        //判断密码不能为空
        if (StringUtils.isEmpty(accountVO.getPassword())){
            throw  new BusinessRuntimeException(BusinessErrors.PARAM_CANNOT_EMPTY,"密码不能为空");
        }
        //从数据库中调数据
        AccountPO accountPO = accountAPIService.checkLogin(CommonsUtils.toPO(accountVO));
        if(null == accountPO){
            throw new BusinessRuntimeException(BusinessErrors.DATA_NOT_EXIST,"账户不存在");
        }
        //判断密码是否相等
        String unVerifedPassword = CommonsUtils.HashPassword(accountPO.getPasswordSalt(), accountVO.getPassword());
        if(!unVerifedPassword.equals(accountPO.getPassword())){
            throw new BusinessRuntimeException(BusinessErrors.DATA_STATUS_ERROR,"密码错误");
        }
        return (AccountVO) CommonsUtils.toVO(accountPO);
    }
}
