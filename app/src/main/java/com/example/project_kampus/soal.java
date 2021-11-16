package com.example.project_kampus;

import com.google.gson.annotations.SerializedName;

public class soal{
    @SerializedName("sid")
    private String msid;
    @SerializedName("isi")
    private String misi;
    @SerializedName("pil")
    private pil mpil;
    @SerializedName("jb")
    private String mjb;
    @SerializedName("wk")
    private String mwk;
    @SerializedName("ss")
    private String mss;

    public soal(String sid, String isi, pil pil, String jb, String wk, String ss){
        msid = sid;
        misi = isi;
        mpil = pil;
        mjb = jb;
        mwk = wk;
        mss = ss;
    }

    public String getMwk() {
        return mwk;
    }

    public String getMss() {
        return mss;
    }

    public String getMjb() {
        return mjb;
    }

    public String getMisi() {
        return misi;
    }

    public String getMsid() {
        return msid;
    }

    public pil getMpil() {
        return mpil;
    }
}
