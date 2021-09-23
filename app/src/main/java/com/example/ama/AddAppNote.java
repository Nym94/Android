package com.example.ama;

import android.graphics.drawable.Drawable;

public class AddAppNote {
    int _id;
    //Drawable selectedAppIcon;
    String appIcon;
    String appName;
    String appFunc;

    public AddAppNote(int _id, String appIcon, String appName, String appFunc) {
        this._id = _id;
        this.appIcon = appIcon;
        this.appName = appName;
        this.appFunc = appFunc;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSelectedAppIcon() {
        return appIcon;
    }

    public void setSelectedAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getSelectedAppName() {
        return appName;
    }

    public void setSelectedAppName(String appName) {
        this.appName = appName;
    }

    public String getSelectedAppFunc() {
        return appFunc;
    }

    public void setSelectedAppFunc(String appFunc) {
        this.appFunc = appFunc;
    }
}
