package com.vst.retrofitdemo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author zwy
 * @email 16681805@qq.com
 * created on 2017/2/23
 * class description:请输入类描述
 */
public class Result<T> {
    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("rs")
    public T rs;
}
