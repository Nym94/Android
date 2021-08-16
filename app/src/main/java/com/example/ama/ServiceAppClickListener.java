package com.example.ama;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class ServiceAppClickListener extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Intent intetnToFrag3 = new Intent();
            InstalledAppNote AppInfo = (InstalledAppNote) intetnToFrag3.getParcelableExtra("selectedAppPackageName");

            if(AppInfo.installedAppPackname == event.getPackageName()) {
                Toast.makeText(getApplicationContext(), "Executed app I'm select", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
