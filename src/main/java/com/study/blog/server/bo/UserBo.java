package com.study.blog.server.bo;

/**
 * Describe:
 * Created by pengp on 2018/1/15.
 */
public class UserBo {
    private String accountNo;
    private String accountName;
    private String userNo;
    private String accountEmail;
    private String accountPhone;
    private String userName;
    private String accountRoleNo;
    private String accountRoleName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountRoleNo() {
        return accountRoleNo;
    }

    public void setAccountRoleNo(String accountRoleNo) {
        this.accountRoleNo = accountRoleNo;
    }

    public String getAccountRoleName() {
        return accountRoleName;
    }

    public void setAccountRoleName(String accountRoleName) {
        this.accountRoleName = accountRoleName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
