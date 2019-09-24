package com.example.split;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class Upload {
    private String mImageUrl;
    private String name;
    private ArrayList<String> members = new ArrayList<>();

    public Upload(Task<Uri> downloadUrl)
    {

    }

    public Upload(String mImageUrl, String name, ArrayList<String> arrayList) {
        this.mImageUrl = mImageUrl;
        this.name = name;
        this.members = arrayList;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getArrayList() {
        return members;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.members = arrayList;
    }
}
