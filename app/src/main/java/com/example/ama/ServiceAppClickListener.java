package com.example.ama;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class ServiceAppClickListener extends AccessibilityService {

    private static ServiceAppClickListener sharedIntanceForSAC;
    String selectedAppPackagename;

    public void setSelectedAppPackagename(String selectedAppPackagename) {
        this.selectedAppPackagename = selectedAppPackagename;
    }

    @Override
    protected void onServiceConnected() {
        //super.onServiceConnected();

        sharedIntanceForSAC = ServiceAppClickListener.this;

        Log.d("packagename", "service connected");
        Log.d("packagename", "selectedAppPackagename: " + selectedAppPackagename);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d("packagename", "event.getPackagename : " + event.getPackageName());
        Log.d("packagename", "selectedAppPackagename: " + selectedAppPackagename);
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (event.getPackageName().equals(selectedAppPackagename)) {
                Log.d("packagename", "Done~~~~~~~~~!!");
                Toast.makeText(getApplicationContext(), "Executed app I'm select", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static ServiceAppClickListener getSharedInstance() {
        return sharedIntanceForSAC;
    }

    @Override
    public void onInterrupt() {

    }
}