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


public class ServiceAppClickListener extends AccessibilityService {

    private static ServiceAppClickListener _sharedIntanceForSAC;
    Set<String> _selectedAppPackageNameSet = new HashSet<>();
    //SharedPreferences _shPref = getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);

    public void addSelectedAppPackage(String selectedAppPackageName) {
        SharedPreferences shPref = getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);

        _selectedAppPackageNameSet.add(selectedAppPackageName);
        shPref.edit().putStringSet("selectedAppPackage", _selectedAppPackageNameSet);
    }

    public void removeSelectedAppPackage(String selectedAppPackgeName) {
        SharedPreferences shPref = getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);

        if (_selectedAppPackageNameSet.contains(selectedAppPackgeName)) {
            _selectedAppPackageNameSet.remove(selectedAppPackgeName);
            shPref.edit().putStringSet("selectedAppPackage", _selectedAppPackageNameSet);
        }
    }

    @Override
    protected void onServiceConnected() {
        //super.onServiceConnected();

        _sharedIntanceForSAC = ServiceAppClickListener.this;
        SharedPreferences shPref = getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);

        if (shPref.getStringSet("selectedAppPackage", new HashSet<String>()) != null) {
            Log.d("packageName", "new service");
            _selectedAppPackageNameSet = shPref.getStringSet("selectedAppPackage", new HashSet<String>());
        }

        Log.d("packageName", "service connected");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d("packageName", "event.getPackageName(onAccessibilityEvent) : " + event.getPackageName());
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Iterator iter = _selectedAppPackageNameSet.iterator();
            while(iter.hasNext())
                Log.d("packageName", iter.next().toString());
            if (_selectedAppPackageNameSet.contains(event.getPackageName().toString())) {
                Log.d("packageName", "Done~~~~~~~~~!!");
                Toast.makeText(getApplicationContext(), "Executed app I'm select", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static ServiceAppClickListener getSharedInstance() {
        return _sharedIntanceForSAC;
    }

    @Override
    public void onInterrupt() {
        Log.d("packageName", "Enter the onInterrupt()");
    }
}