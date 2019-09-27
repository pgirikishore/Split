package com.example.split;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class Upload {
    private String mImageUrl;
    private String name;
    private String createdBy;
    private ArrayList<String> members = new ArrayList<>();
    private ArrayList<Double> NetAmt= new ArrayList<>();

    public Upload()
    {

    }

    public Upload(String mImageUrl, String name, String createdBy, ArrayList<String> members, ArrayList<Double> netAmt) {
        this.mImageUrl = mImageUrl;
        this.name = name;
        this.createdBy = createdBy;
        this.members = members;
        NetAmt = netAmt;
    }


    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public ArrayList<Double> getNetAmt() {
        return NetAmt;
    }

    public void setNetAmt(ArrayList<Double> netAmt) {
        NetAmt = netAmt;
    }
}
