package com.example.ama;

public class AddAppNote {
    int _id;
    String appImage;
    String appName;
    String appFunc;

    public AddAppNote(int _id, String appImage, String appName, String appFunc) {
        this._id = _id;
        this.appImage = appImage;
        this.appName = appName;
        this.appFunc = appFunc;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAppImage() {
        return appImage;
    }

    public void setAppImage(String appImage) {
        this.appImage = appImage;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppFunc() {
        return appFunc;
    }

    public void setAppFunc(String appFunc) {
        this.appFunc = appFunc;
    }
}
