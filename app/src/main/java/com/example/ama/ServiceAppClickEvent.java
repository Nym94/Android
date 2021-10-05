package com.example.ama;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.PopupWindow;
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
            Log.d("packageName", "PW : " + appPassword);
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

            shPrefEdit.remove(appPackageName + "_pw");
            shPrefEdit.remove(appPackageName + "_pwState");
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
//            Iterator iter = _appPackageNameSet.iterator();
//            while(iter.hasNext())
//                Log.d("packageName", iter.next().toString());
            if (_appPackageNameSet.contains(event.getPackageName().toString())) {
                SharedPreferences shPref = getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);

                // Priority : Password < Time limit
                if (shPref.getBoolean(event.getPackageName().toString() + "_pwState", false)) {
                    gotoHome();

                    Intent intentToDialog = new Intent(this, DialogCheckPassword.class);
                    intentToDialog.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intentToDialog.putExtra("appPackageName", event.getPackageName().toString());
                    intentToDialog.putExtra("appPassword", shPref.getString(event.getPackageName().toString() + "_pw", ""));
                    startActivity(intentToDialog);

//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            DialogCheckPassword dialogCheckPassword = DialogCheckPassword.getSharedInstance();
//
//                            dialogCheckPassword.acceptButtonClickListener(new OnItemClickListener() {
//                                @Override
//                                public void onItemClick(int position) {
//
//                                  dialogCheckPassword.finish();
//                                }
//                            });
//                            dialogCheckPassword.cancelButtonClickListener(new OnItemClickListener() {
//                                @Override
//                                public void onItemClick(int position) {
//                                    dialogCheckPassword.finish();
//                                }
//                            });
//                        }
//                    }, 3000);
                }
            }
        }
    }

    public static ServiceAppClickEvent getSharedInstance() {
        return _sharedIntanceForSAC;
    }

    private void gotoHome(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                | Intent.FLAG_ACTIVITY_FORWARD_RESULT
                | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(intent);
    }

    @Override
    public void onInterrupt() {
        Log.d("packageName", "Enter the onInterrupt()");
    }
}