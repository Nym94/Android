package com.example.ama;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class Fragment3MainMenu extends Fragment {

    boolean _useAnyFunc = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3_main_menu, container, false);

        initUI(rootView);

        return rootView;
    }

    /* Only use basic construct in fragment, because overloaded construct not guaranteed to call.
    public Fragment3MainMenu(InstalledAppNote selectedAppInfo) {
        this.selectedAppInfo = selectedAppInfo;
    }
    */

    public void initUI(ViewGroup rootView) {

        ImageView appIcon = rootView.findViewById(R.id.SelectedAppIcon2);
        TextView appName = rootView.findViewById(R.id.SelectedAppName2);
        Button saveButton = rootView.findViewById(R.id.button_save);
        Button cancelButton = rootView.findViewById(R.id.button_cancel);

        Bundle bundleFromFragment2 = getArguments();
        InstalledAppNote selectedAppInfo = (InstalledAppNote) bundleFromFragment2.getParcelable("selectedAppInfo");

        appIcon.setImageDrawable(selectedAppInfo.getInstalledAppIcon());
        appName.setText(selectedAppInfo.getInstalledAppName());

        funcdef_SetPassword(rootView, selectedAppInfo);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServiceAppClickListener serviceAppClickListener = ServiceAppClickListener.getSharedInstance();

                if (_useAnyFunc == true) {
                    serviceAppClickListener.addSelectedAppPackage(selectedAppInfo.installedAppPackageName);
                }
                else if (_useAnyFunc == false) {
                    serviceAppClickListener.removeSelectedAppPackage(selectedAppInfo.installedAppName);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // use SharedPreference
            }
        });
    }

    public void funcdef_SetPassword(ViewGroup rootView, InstalledAppNote selectedAppInfo) {

        LinearLayout layout_setPassword = rootView.findViewById(R.id.layout_set_password);
        Switch switch_setPassword = rootView.findViewById(R.id.switch_set_password);

        layout_setPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "set password", Toast.LENGTH_LONG).show();
            }
        });

        switch_setPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_setPassword.isChecked() == true) {
                    // Show the dialog for set the app's password
                    AlertDialog.Builder dialogSetPassword = new AlertDialog.Builder(getActivity());
                    dialogSetPassword.setTitle("비밀번호 설정").setMessage("비밀번호를 설정하세요.")
                    .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialogSetPassword.show();
                    _useAnyFunc = true;
                }

                if (switch_setPassword.isChecked() == false) {
                    _useAnyFunc = false;
                }
            }
        });
    }
}