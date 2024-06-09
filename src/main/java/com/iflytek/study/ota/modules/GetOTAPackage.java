package com.iflytek.study.ota.modules;

import com.google.gson.annotations.SerializedName;

public class GetOTAPackage {

    public static final String ACCESS_KEY = "bba93cde";
    public static final String ACCESS_KEY_SECRET = "15d8e25cb9d6b73bf6bbb9dc417d82c";

    @SerializedName("base")
    private Base base;

    // Getter 和 Setter
    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    // 嵌套的Base类
    public static class Base {



    }
}