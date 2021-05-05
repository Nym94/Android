package com.example.ama;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment2InstalledAppList extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2_installed_app, container, false);

        initUI(rootView);

        return rootView;
    }

    public void initUI(ViewGroup rootView) {

        RecyclerView recyclerView = rootView.findViewById(R.id.recycleInstalledApp);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        InstalledAppNoteAdapter installedAppNoteAdapter = new InstalledAppNoteAdapter();

        List<ApplicationInfo> packages = getActivity().getPackageManager().getInstalledApplications(0);

        int id = 0;
        Drawable appIcon;
        String appName = "";

        for(ApplicationInfo appInfo : packages) {

            appIcon = getActivity().getPackageManager().getApplicationIcon(appInfo);

            try {
                appName = getActivity().getPackageManager().getApplicationLabel(
                        getActivity().getPackageManager().getApplicationInfo(appInfo.packageName, 0)//PackageManager.GET_UNINSTALLED_PACKAGES)
                ).toString();
            } catch (PackageManager.NameNotFoundException e) {
            }

            installedAppNoteAdapter.addItem(new InstalledAppNote(id, appIcon, appName));
            id++;
        }
        id = 0;

        recyclerView.setAdapter(installedAppNoteAdapter);




    }
}