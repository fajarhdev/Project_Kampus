package com.example.project_kampus;

import com.google.gson.annotations.SerializedName;

public class pil {
    @SerializedName("a")
    private String ma;
    @SerializedName("b")
    private String mb;
    @SerializedName("c")
    private String mc;

    public pil(String a, String b, String c){
        ma = a;
        mb = b;
        mc = c;
    }

    public String getMb() {
        return mb;
    }

    public String getMa() {
        return ma;
    }

    public String getMc() {
        return mc;
    }
}
