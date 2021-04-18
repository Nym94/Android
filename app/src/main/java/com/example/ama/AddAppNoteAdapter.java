package com.example.ama;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddAppNoteAdapter extends RecyclerView.Adapter<AddAppNoteAdapter.ViewHolder> {

    ArrayList<AddAppNote> items = new ArrayList<AddAppNote>();

    OnItemClickListener listener;

    int layoutType = 0;
    int adapterPosition = 0;

    @NonNull
    @Override
    public AddAppNoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.add_app_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AddAppNote item = items.get(position);
        holder.setItem(item);
        holder.setLayoutType(layoutType);
        adapterPosition = position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(AddAppNote item) {
        items.add(item);
    }

    public void setItems(ArrayList<AddAppNote> items) {
        this.items = items;
    }

    public AddAppNote getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout emptyAppLayout;
        LinearLayout setAppLayout;

        ImageView appImage;
        TextView appName;
        TextView appFunc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            emptyAppLayout = itemView.findViewById(R.id.emptyAppLayout);
            setAppLayout = itemView.findViewById(R.id.setAppLayout);

            appImage = itemView.findViewById(R.id.app_image);
            appName = itemView.findViewById(R.id.app_name);
            appFunc = itemView.findViewById(R.id.app_func);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void setItem(AddAppNote item) {
            //appImage.setImageResource(item.getAppImage());
            appName.setText(item.getAppName());
            appFunc.setText(item.getAppFunc());
        }

        public void setLayoutType(int layoutType) {
            if (layoutType == 0) {
                emptyAppLayout.setVisibility(View.VISIBLE);
                setAppLayout.setVisibility(View.GONE);
            } else if (layoutType == 1) {
                emptyAppLayout.setVisibility(View.GONE);
                setAppLayout.setVisibility(View.VISIBLE);
            }
        }
    }
}
