package com.example.project_kampus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class hasil {
    @SerializedName("uid")
    private int muid;
    @SerializedName("transkrip")
    private List<transkrip> mtranskrip;

    public hasil(int uid, List<transkrip> transkrip){
        muid = uid;
        mtranskrip = transkrip;
    }

}
