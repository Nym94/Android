package com.example.ama;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class InstalledAppNote implements Parcelable {

    int _id;
    String appPackageName;
    Drawable appIcon;
    String appName;

    public InstalledAppNote(int _id, String packageName, Drawable appIcon, String appName) {
        this._id = _id;
        this.appPackageName = packageName;
        this.appIcon = appIcon;
        this.appName = appName;
    }

    /* // Necessary?
    public InstalledAppNote (Parcel src) {
        readFromParcel(src);
    }
    */

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getInstalledAppPackname() { return this.appPackageName; }

    public void setInstalledAppPackname(String appPackageName) { this.appPackageName = appPackageName; }

    public String getInstalledAppName() {
        return this.appName;
    }

    public void setInstalledAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getInstalledAppIcon() {
        return this.appIcon;
    }

    public void setInstalledAppIcon(Drawable appIcon) { this.appIcon = appIcon; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(_id);
        Bitmap bitmap = (Bitmap)((BitmapDrawable) appIcon).getBitmap();
        dest.writeParcelable(bitmap, flags);
        dest.writeString(appName);
    }

    /* // Necessary?
    public void readFromParcel(Parcel src) {
        _id = src.readInt();
        Bitmap bitmap = (Bitmap)((BitmapDrawable) installedAppIcon).getBitmap();
        //installedAppIcon = new BitmapDrawable(bitmap);
        installedAppName = src.readString();
    }
    */

    public static final Parcelable.Creator<InstalledAppNote> CREATOR =
            new Creator<InstalledAppNote>() {
                @Override
                public InstalledAppNote createFromParcel(Parcel source) {
                    return null;
                    //return new InstalledAppNote(source);
                }

                @Override
                public InstalledAppNote[] newArray(int size) {
                    return new InstalledAppNote[size];
                }
            };
}
