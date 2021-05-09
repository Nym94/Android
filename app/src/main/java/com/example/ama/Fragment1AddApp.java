package com.example.ama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1_add_app, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        RecyclerView recyclerView;
        recyclerView = rootView.findViewById(R.id.recycleAddApp);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);   // it decides recyclerView's layout form(Linear layout).

        AddAppNoteAdapter addAppNoteAdapter = new AddAppNoteAdapter();

        addAppNoteAdapter.addItem(new AddAppNote(0, "null", "null", "null"));
        //addAppNoteAdapter.addItem(new AddAppNote(0, "null", "null", "null"));
        //addAppNoteAdapter.addItem(new AddAppNote(0, "null", "null", "null"));

        recyclerView.setAdapter(addAppNoteAdapter);     // 이 함수 위치에 따른 결과 확인

        addAppNoteAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AddAppNote item = addAppNoteAdapter.getItem(position);
                //addAppNoteAdapter.addItem(new AddAppNote(0, "null", "null", "null"));
                //addAppNoteAdapter.notifyDataSetChanged();


                //Toast.makeText(getContext(), "아이템#" + position + " 선택됨", Toast.LENGTH_LONG).show();

                // Switch to fragment2, and send "item" of fragment1
                Fragment fragment2 = new Fragment2InstalledAppList();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();

            }
        });
    }
}