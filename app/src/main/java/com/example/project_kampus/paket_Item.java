package com.example.project_kampus;

import com.google.gson.annotations.SerializedName;

public class paket_Item {
    @SerializedName("ns")
    private String mns;
    @SerializedName("lv")
    private String mlv;
    @SerializedName("plink")
    private String mpic;
    @SerializedName("np")
    private String mname;
    @SerializedName("ds")
    private String mdesc;

    public paket_Item(String ns, String lv, String pic, String name, String desc) {
        mns = ns;
        mlv = lv;
        mpic = pic;
        mdesc = desc;
        mname = name;
    }

    public String getMlv() {
        return mlv;
    }

    public String getMns() {
        return mns;
    }

    public String getMpic() {
        return mpic;
    }

    public String getMdesc() {
        return mdesc;
    }

    public String getMname() {
        return mname;
    }
}
