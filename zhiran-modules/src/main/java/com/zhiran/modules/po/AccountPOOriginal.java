package com.zhiran.modules.po;

import com.zhiran.common.domin.po.PO;

public class AccountPOOriginal implements PO {


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
   private String username;
   private String password;
   private String passwordSalt;
    @Override
    public Class getVO() {
        return null;
    }
}
