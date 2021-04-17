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

    int layoutType = 0;

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
    }

    @Override
    public int getItemCount() {

        //return 0;
        return items.size();   //이거 결과가 어떻게 되는지 확인
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
