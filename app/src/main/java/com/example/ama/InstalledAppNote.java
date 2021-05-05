package com.example.ama;

import android.graphics.drawable.Drawable;

public class InstalledAppNote {

    int _id;
    Drawable installedAppIcon;
    String installedAppName;

    public InstalledAppNote(int _id, Drawable appIcon, String appName) {
        this._id = _id;
        this.installedAppIcon = appIcon;
        this.installedAppName = appName;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getInstalledAppName() {
        return installedAppName;
    }

    public void setInstalledAppName(String AppName) {
        this.installedAppName = AppName;
    }

    public Drawable getInstalledAppIcon() {
        return installedAppIcon;
    }

    public void setInstalledAppIcon(Drawable AppIcon) {
        this.installedAppIcon = AppIcon;
    }
}
