package com.immymemine.kevin.myandroidstudy.Output;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DrawingActivity extends AppCompatActivity {
    CustomViewDrawables drawables;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawables = new CustomViewDrawables(this);
        setContentView(drawables);
    }
}
