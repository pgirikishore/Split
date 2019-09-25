package com.example.split;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class Upload {
    private String mImageUrl;
    private String name;
    private ArrayList<String> members = new ArrayList<>();

    public Upload()
    {

    }

    public Upload(String mImageUrl, String name, ArrayList<String> members) {
        this.mImageUrl = mImageUrl;
        this.name = name;
        this.members = members;
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

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }
}
