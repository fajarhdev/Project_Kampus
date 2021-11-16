package com.example.project_kampus;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class content {
    @SerializedName("content")
    private List<soal> mcontent;
    public content(List<soal> soalList){
        mcontent = soalList;
    }

    public List<soal> getContent() {
        return mcontent;
    }
}


