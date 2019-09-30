package com.example.split;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class Upload {
    private String mImageUrl;
    private String name;
    private ArrayList<String> Viewers  = new ArrayList<>();
    private ArrayList<String> members = new ArrayList<>();
    private ArrayList<Double> NetAmt= new ArrayList<>();

    public Upload()
    {

    }

    public Upload(String mImageUrl, String name, ArrayList<String> viewers, ArrayList<String> members, ArrayList<Double> netAmt) {
        this.mImageUrl = mImageUrl;
        this.name = name;
        Viewers = viewers;
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

    public ArrayList<String> getViewers() {
        return Viewers;
    }

    public void setViewers(ArrayList<String> viewers) {
        Viewers = viewers;
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
