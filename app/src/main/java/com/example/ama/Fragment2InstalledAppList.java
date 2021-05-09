package com.example.ama;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

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

        PackageManager pm = getActivity().getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent, 0);

        int id = 0;
        Drawable appIcon;
        String appName = "";

        //for(ApplicationInfo appInfo : packages) {
        for(ResolveInfo resInfo : resolveInfos) {
            //if ((resInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                //appIcon = getActivity().getPackageManager().getApplicationIcon(appInfo);
                appIcon = resInfo.loadIcon(pm);
                appName = resInfo.loadLabel(pm).toString();

            /*
            try {
                appName = getActivity().getPackageManager().getApplicationLabel(
                        getActivity().getPackageManager().getApplicationInfo(appInfo.packageName, 0)//PackageManager.GET_UNINSTALLED_PACKAGES)
                ).toString();
            } catch (PackageManager.NameNotFoundException e) {
            }
            */

                installedAppNoteAdapter.addItem(new InstalledAppNote(id, appIcon, appName));
                id++;
            //}
        }

        Toast.makeText(getContext(), "The number of app : " + id, Toast.LENGTH_LONG).show();
        id = 0;

        recyclerView.setAdapter(installedAppNoteAdapter);




    }
}