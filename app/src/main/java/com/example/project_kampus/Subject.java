package com.example.project_kampus;

import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("plink")
    private String Subject_pic;
    @SerializedName("ns")
    private String Subject;
    @SerializedName("ds")
    private String Subject_Desc;
    @SerializedName("ds2")
    private String Subject_Desc2;

    public Subject(String subject_pic, String subject, String subject_Desc, String subject_Desc2){
        Subject = subject;
        Subject_pic = subject_pic;
        Subject_Desc = subject_Desc;
        Subject_Desc2 = subject_Desc2;
    }

    public void changeText(String text){
        Subject_Desc = text;
    }
    public String getSubject_pic(){return Subject_pic;}
    public String getSubject(){return Subject;}
    public String getSubject_Desc(){return Subject_Desc;}
    public String getSubject_Desc2(){return Subject_Desc2;}
}
