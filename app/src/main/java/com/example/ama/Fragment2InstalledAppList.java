package com.example.ama;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
        //List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent, 0);

        int id = 0;
        String appPackname;
        Drawable appIcon;
        String appName;

        //for(ApplicationInfo appInfo : packages) {
        for(ResolveInfo resInfo : resolveInfos) {

            appPackname = resInfo.activityInfo.applicationInfo.packageName;
            appIcon = resInfo.loadIcon(pm);
            appName = resInfo.loadLabel(pm).toString();

            installedAppNoteAdapter.addItem(new InstalledAppNote(id, appPackname, appIcon, appName));
            id++;
        }

        Toast.makeText(getContext(), "The number of app : " + id, Toast.LENGTH_LONG).show();
        id = 0;

        recyclerView.setAdapter(installedAppNoteAdapter);

        installedAppNoteAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                InstalledAppNote item = installedAppNoteAdapter.getItem(position);

                // Change to fragment3, and send "item" of fragment2
                Fragment fragment3 = new Fragment3MainMenu();

                //Intent intentToFragment3 = new Intent();
                //intentToFragment3.putExtra("selectedAppInfo", item);

                Bundle bundleToFragment3 = new Bundle();
                //bundleForFragment3.putParcelable("selectedAppInfo", intentToFragment3.getParcelableExtra("selectedAppInfo"));
                bundleToFragment3.putParcelable("selectedAppInfo", item);
                fragment3.setArguments(bundleToFragment3);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.container, fragment3);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
            }
        });
    }
}