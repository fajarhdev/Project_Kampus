package com.example.project_kampus;

import com.google.gson.annotations.SerializedName;

public class transkrip {
    @SerializedName("sid")
    private int sid;
    @SerializedName("st")
    private String st;
    @SerializedName("wj")
    private int wj;
    @SerializedName("js")
    private int js;

    public transkrip(int sid, int js, String st, int wj){
        this.sid = sid;
        this.js = js;
        this.st = st;
        this.wj = wj;
    }
}
