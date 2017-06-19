package com.zgh.MVPDemo_Login.bean;

/**
 * Created by ZGH on 2017/6/16.
 */

/**
 * (1)首先我们需要一个UserBean，用来保存用户信息
 */
public class UserBean {
    private String name;
    private String password;

    public UserBean(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
