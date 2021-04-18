package com.example.ama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment1AddApp extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_add_app, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        RecyclerView recyclerView;
        recyclerView = rootView.findViewById(R.id.recyclerApp);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);   // it decides recyclerView's layout form

        AddAppNoteAdapter addAppNoteAdapter = new AddAppNoteAdapter();
        recyclerView.setAdapter(addAppNoteAdapter);     // 이 함수 위치에 따른 결과 확인

        /*
        addAppNoteAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick() {
                AddAppNote item = addAppNoteAdapter.getItem(position);

            }
        });
        */



    }
}
