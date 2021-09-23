package com.example.ama;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class ServiceAppClickEvent extends AccessibilityService {

    private static ServiceAppClickEvent _sharedIntanceForSAC;
    Set<String> _appPackageNameSet = new HashSet<>();
    //char[] _appFuncState = {'F'};
    //SharedPreferences _shPref = getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);

    public void addSelectedAppPackage(String appPackageName, String appPassword) {
        SharedPreferences shPref = getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor shPrefEdit = shPref.edit();

        _appPackageNameSet.add(appPackageName);
        shPrefEdit.putStringSet("selectedAppPackage", _appPackageNameSet);

        if (!appPassword.equals("") && appPassword != "") {
            shPrefEdit.remove(appPackageName + "_pw");
            shPrefEdit.putString(appPackageName + "_pw", appPassword);
            shPrefEdit.putBoolean(appPackageName + "_pwState", true);
            Log.d("packageName", "Enter~~");
        }
        else {
            shPrefEdit.putBoolean(appPackageName + "_pwState", false);
        }
        shPrefEdit.commit();
    }

    public void removeSelectedAppPackage(String appPackageName) {
        SharedPreferences shPref = getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor shPrefEdit = shPref.edit();

        if (_appPackageNameSet.contains(appPackageName)) {
            _appPackageNameSet.remove(appPackageName);
            shPrefEdit.putStringSet("selectedAppPackage", _appPackageNameSet);
        }

        shPrefEdit.commit();
    }

    @Override
    protected void onServiceConnected() {
        //super.onServiceConnected();

        _sharedIntanceForSAC = ServiceAppClickEvent.this;
        SharedPreferences shPref = getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);

        if (shPref.getStringSet("selectedAppPackage", new HashSet<String>()) != null) {
            Log.d("packageName", "new service");
            _appPackageNameSet = shPref.getStringSet("selectedAppPackage", new HashSet<String>());
            Iterator iter = _appPackageNameSet.iterator();
            while(iter.hasNext())
                Log.d("packageName", iter.next().toString());
        }

        Log.d("packageName", "service connected");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d("packageName", "event.getPackageName(onAccessibilityEvent) : " + event.getPackageName());
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Iterator iter = _appPackageNameSet.iterator();
            while(iter.hasNext())
                Log.d("packageName", iter.next().toString());
            if (_appPackageNameSet.contains(event.getPackageName().toString())) {
                Log.d("packageName", "Done~~~~~~~~~!!");
                Toast.makeText(getApplicationContext(), "Executed app I'm select", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static ServiceAppClickEvent getSharedInstance() {
        return _sharedIntanceForSAC;
    }

    @Override
    public void onInterrupt() {
        Log.d("packageName", "Enter the onInterrupt()");
    }
}