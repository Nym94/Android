package com.example.ama;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InstalledAppNoteAdapter extends RecyclerView.Adapter<InstalledAppNoteAdapter.ViewHolder>{

    ArrayList<InstalledAppNote> items = new ArrayList<InstalledAppNote>();

    OnItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.installed_app_item, parent, false);

        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InstalledAppNote item = getItem(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public InstalledAppNote getItem(int position) {
        return items.get(position);
    }

    public void addItem(InstalledAppNote item) {
        items.add(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView AppIcon;
        TextView AppName;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            AppIcon = itemView.findViewById(R.id.installedAppIcon);
            AppName = itemView.findViewById(R.id.installedAppName);
        }

        public void setItem(InstalledAppNote item) {
            AppIcon.setImageDrawable(item.getInstalledAppIcon());
            AppName.setText(item.getInstalledAppName());
        }
    }
}
