package com.example.demosqlite.model;

public class Student {
    private  int mID;
    private String mFullName;
    private String mClass;

    public Student() {
    }

    public Student(String mFullName, String mClass) {
        this.mFullName = mFullName;
        this.mClass = mClass;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getmClass() {
        return mClass;
    }

    public void setmClass(String mClass) {
        this.mClass = mClass;
    }
}
