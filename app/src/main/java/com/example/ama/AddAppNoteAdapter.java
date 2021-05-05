package com.example.ama;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AddAppNoteAdapter extends RecyclerView.Adapter<AddAppNoteAdapter.ViewHolder> {

    ArrayList<AddAppNote> items = new ArrayList<AddAppNote>();

    OnItemClickListener listener;

    @NonNull
    @Override
    public AddAppNoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Log.d("Log", "onCreate");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.add_app_item, parent, false);

        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Log.d("Log", "onBind");
        AddAppNote item = getItem(position);
        holder.setItem(item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(AddAppNote item) { items.add(item); }

    public void setItems(ArrayList<AddAppNote> items) { this.items = items; }

    public AddAppNote getItem(int position) { return items.get(position); }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout emptyAppLayout;
        LinearLayout setAppLayout;

        ImageView AppIcon;
        TextView AppName;
        TextView AppFunc;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            emptyAppLayout = itemView.findViewById(R.id.emptyAppLayout);
            setAppLayout = itemView.findViewById(R.id.setAppLayout);

            AppIcon = itemView.findViewById(R.id.SelectedAppIcon);
            AppName = itemView.findViewById(R.id.SelectedAppName);
            AppFunc = itemView.findViewById(R.id.SelectedAppFunc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if (listener != null)
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void setItem(AddAppNote item) {
            //appIcon.setImageResource(item.getAppIcon());
            AppName.setText(item.getSelectedAppName());
            AppFunc.setText(item.getSelectedAppFunc());
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