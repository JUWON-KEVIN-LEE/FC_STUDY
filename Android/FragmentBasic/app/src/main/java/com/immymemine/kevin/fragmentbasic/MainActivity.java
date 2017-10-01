package com.immymemine.kevin.fragmentbasic;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CustomAdapter.Call{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void init() {
        if(getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            initFrag();
        }
    }

    private void initFrag() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, new ListFragment()).commit();
    }

    @Override
    public void goDetail() {

    }
}
