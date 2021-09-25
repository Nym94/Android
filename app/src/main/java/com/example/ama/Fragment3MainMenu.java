package com.example.ama;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.zip.Inflater;

public class Fragment3MainMenu extends Fragment {

    String _appPassword = "";

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

        //For set password
        LinearLayout layout_setPassword = rootView.findViewById(R.id.layout_set_password);
        Switch switch_setPassword = rootView.findViewById(R.id.switch_setPassword);

        //Initialize to the previous set value using SharedPreference
        SharedPreferences shPref = getActivity().getSharedPreferences("packageNamePref", Activity.MODE_PRIVATE);
        boolean prevState[] = { false };
        prevState[0] = shPref.getBoolean(selectedAppInfo.appPackageName + "_pwState", false);

        if (prevState[0]) {
            switch_setPassword.setChecked(true);
        }

        layout_setPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_setPassword(rootView);
            }
        });

        switch_setPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch_setPassword.isChecked() == true) {
                    switch_setPassword.setChecked(false);
                    func_setPassword(rootView);
                }
                else {
                    _appPassword = "";
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the any function's state has changed.
                boolean checkState = false;
                boolean curState[] = { false };
                curState[0] = switch_setPassword.isChecked();

                for (int i = 0; i < curState.length; i++) {
                    if (curState[i] == true) {
                        checkState = true;
                        break;
                    }
                }

                // For send "item" of fragment3(App's information, Function)
                Fragment fragment1 = new Fragment1AddApp();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                Bundle bundleToFragment3 = new Bundle();
                bundleToFragment3.putParcelable("selectedAppInfo", selectedAppInfo);

                fragment1.setArguments(bundleToFragment3);

                if (checkState) {
                    // Send to service for selected app's info.
                    ServiceAppClickEvent serviceAppClickEvent = ServiceAppClickEvent.getSharedInstance();
                    serviceAppClickEvent.addSelectedAppPackage(selectedAppInfo.appPackageName, _appPassword);

                    // Move to fragment1
                    transaction.replace(R.id.container, fragment1);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.commit();

                    Toast.makeText(getContext(), "저장 완료", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

                    dialogBuilder.setMessage("체크된 항목이 없습니다. 그대로 진행하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Move to fragment1
                                    transaction.replace(R.id.container, fragment1);
                                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                                    transaction.commit();

                                    Toast.makeText(getContext(), "저장 완료", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("취소", null);
                    dialogBuilder.show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the any function's state has changed.
                boolean checkChangeState = false;
                boolean curState[] = { false };
                curState[0] = switch_setPassword.isChecked();

                for (int i = 0; i < curState.length; i++) {
                    if (curState[i] != prevState[i]) {
                        checkChangeState = true;
                        break;
                    }
                }

                if (checkChangeState) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

                    dialogBuilder.setMessage("변경된 사항을 저장 안하겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().getSupportFragmentManager().popBackStack();
                                }
                            })
                            .setNegativeButton("취소", null);
                    dialogBuilder.show();
                }
                else {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
    }

    public void func_setPassword(ViewGroup rootView) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_set_password, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogView);

        AlertDialog dialogSetPassword = dialogBuilder.create();

        Button acceptButton = dialogView.findViewById(R.id.button_acceptSetPassword);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textPassword = dialogView.findViewById(R.id.editText_password);
                TextView textConfirmPassword = dialogView.findViewById(R.id.editText_confirmPassword);

                Switch switch_setPassword = rootView.findViewById(R.id.switch_setPassword);
                String pw1 = textPassword.getText().toString();
                String pw2 = textConfirmPassword.getText().toString();

                if (pw1.equals("") || pw2.equals("")) {
                    Toast.makeText(getContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!pw1.equals(pw2)) {
                    Toast.makeText(getContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    _appPassword = pw1;
                    switch_setPassword.setChecked(true);
                    Toast.makeText(getContext(), "비밀번호가 설정되었습니다.", Toast.LENGTH_LONG).show();
                    dialogSetPassword.dismiss();
                }
            }
        });

        Button cancelButton = dialogView.findViewById(R.id.button_cancelSetPassword);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSetPassword.dismiss();
            }
        });

        dialogSetPassword.show();
    }
}