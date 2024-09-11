package com.zhiran.modules.vo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhiran.common.domin.vo.VO;
import com.zhiran.common.enums.InitialResolverType;
import com.zhiran.common.initial.annotation.InitialResolver;
import com.zhiran.modules.po.AccountPO;
import com.zhiran.common.group.Group;
import io.swagger.models.auth.In;
import org.springframework.core.SpringVersion;

import javax.validation.constraints.NotNull;

public class AccountVO implements VO {

    /**
     * 用户主键id
     */
    @InitialResolver(resolver = InitialResolverType.GEN_SNOWFLAKE_ID,groups = {Group.Creat.class})
    @NotNull(message = "id不能为空",groups = {Group.All.class})
    private String id;
    /**
     * 用户名称
     */
    @NotNull(message = "用户名不能为空",groups = {Group.Creat.class,Group.Update.class})
    private String username;
    /**
     * 用户姓名 Power by wangks0504 zhiranAI
     */
    private String useralias;
    /**
     * 用户密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "密码不能为空",groups = {Group.Creat.class,Group.Select.class})
    private String password;
    /**
     * 手机号码
     */
    @NotNull(message = "电话号码不能为空",groups = {Group.Creat.class})
    private String phoneNumber;
    /**
     * 创建时间
     */
    @InitialResolver(resolver = InitialResolverType.CURRENT_DATE,groups = {Group.Creat.class})
    private String createTime;
    /**
     * 创建用户
     */
    @InitialResolver(resolver = InitialResolverType.CURRENT_ACCOUNT,groups = {Group.Creat.class})
    private String createByUser;
    /**
     * 更新时间
     */
    @InitialResolver(resolver = InitialResolverType.CURRENT_DATE,groups = {Group.Update.class})
    private String updateTime;
    /**
     * 更新用户
     */
    @InitialResolver(resolver = InitialResolverType.CURRENT_ACCOUNT,groups = {Group.Creat.class})
    private String updateByUser;
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
    /*******************set AND get*********************/
    /*******************set AND get*********************/
    /*******************set AND get*********************/
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateByUser() {
        return createByUser;
    }

    public void setCreateByUser(String createByUser) {
        this.createByUser = createByUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateByUser() {
        return updateByUser;
    }

    public void setUpdateByUser(String updateByUser) {
        this.updateByUser = updateByUser;
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

    @Override
    public Class getPO() {
        return AccountPO.class;
    }
}
