package com.immymemine.kevin.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    public void initFragment() {
        // 1. Fragment Manager
        FragmentManager manager = getSupportFragmentManager();
        // 2. Fragment Transaction Manager
        FragmentTransaction transaction = manager.beginTransaction();
        Log.d("in onCreate", "__________________before add__________________");
        // 3. Attach Fragment to Layout
        transaction.add(R.id.container, new ListFragment());
        Log.d("in onCreate", "__________________after add__________________");
        // 4. Commit
        transaction.commit();
    }

    public void goDetail(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new DetailFragment())
                .addToBackStack(null) // <- 이 명령어를 호출하면 Transaction 전체를 스택에 담는다
                .commit();
    }

    @Override
    protected void onStart() {
        Log.d("Activity", "__________________start__________________");

        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("Activity", "__________________resume__________________");
        super.onResume();
    }
}
