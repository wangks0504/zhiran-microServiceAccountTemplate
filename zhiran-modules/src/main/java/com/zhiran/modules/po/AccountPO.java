package com.zhiran.modules.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhiran.common.domin.po.PO;
import com.zhiran.common.enums.InitialResolverType;
import com.zhiran.common.group.Group;
import com.zhiran.common.initial.annotation.InitialResolver;
import com.zhiran.modules.vo.AccountVO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AccountPO implements Serializable, PO {
    public AccountPO(){}
    public AccountPO(String accountID){this.id=accountID;}

    /**
     * 用户主键id
     */
    private String id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户姓名 Power by wangks0504 zhiranAI
     */
    private String useralias;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 密码盐值
     */
    private String passwordSalt;



    /**
     * 用户头像存储地址
     */
    private String avatar;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建用户
     */
    private String createBy;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 更新用户
     */
    private String updateBy;
    /**
     * 账户状态
     * @1 正常
     * @0 禁止
     */
    private Integer status;
    /**
     * 乐观锁
     */
    private Integer revision;
    /**
     * Token
     */
    private String Token;
    /**
     * 新密码
     * 用户修改时使用
     */
    private String newPassword;
    /*******************  set AND get  *********************/
    /*******************  set AND get  *********************/
    /*******************  set AND get  *********************/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseralias() {
        return useralias;
    }

    public void setUseralias(String useralias) {
        this.useralias = useralias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public Class getVO() {
        return AccountVO.class;
    }
}
