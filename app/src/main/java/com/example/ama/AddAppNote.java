package com.example.ama;

import android.graphics.drawable.Drawable;

public class AddAppNote {
    int _id;
    //Drawable selectedAppIcon;
    String selectedAppIcon;
    String selectedAppName;
    String selectedAppFunc;

    public AddAppNote(int _id, String appIcon, String appName, String appFunc) {
        this._id = _id;
        this.selectedAppIcon = appIcon;
        this.selectedAppName = appName;
        this.selectedAppFunc = appFunc;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSelectedAppIcon() {
        return selectedAppIcon;
    }

    public void setSelectedAppIcon(String appIcon) {
        this.selectedAppIcon = appIcon;
    }

    public String getSelectedAppName() {
        return selectedAppName;
    }

    public void setSelectedAppName(String appName) {
        this.selectedAppName = appName;
    }

    public String getSelectedAppFunc() {
        return selectedAppFunc;
    }

    public void setSelectedAppFunc(String appFunc) {
        this.selectedAppFunc = appFunc;
    }
}
