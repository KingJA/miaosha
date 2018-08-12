package com.kingja.miaosha.vo;

import com.kingja.miaosha.validator.IsMobile;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Description:TODO
 * Create Time:2018/8/11 18:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min = 32)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
