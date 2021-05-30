package com.example.ama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment fragment1;
    //Fragment fragment2;
    Fragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1AddApp();
        //fragment3 = new Fragment3MainMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
    }
}