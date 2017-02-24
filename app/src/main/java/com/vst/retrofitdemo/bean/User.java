package com.vst.retrofitdemo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author zwy
 * @email 16681805@qq.com
 * created on 2017/2/23
 * class description:请输入类描述
 */
public class User {
    @SerializedName("userName")
    private String userName;
    @SerializedName("age")
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
