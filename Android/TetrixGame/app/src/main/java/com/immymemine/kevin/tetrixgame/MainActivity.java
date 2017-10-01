package com.immymemine.kevin.tetrixgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    Button downBtn, leftBtn, rightBtn, upBtn;
    FrameLayout stageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Stage stage = new Stage(this);
        stageLayout.addView(stage);

    }

    private void initView() {
        downBtn = (Button) findViewById(R.id.downBtn);
        leftBtn = (Button) findViewById(R.id.leftBtn);
        rightBtn = (Button) findViewById(R.id.rightBtn);
        upBtn = (Button) findViewById(R.id.upBtn);
        stageLayout = (FrameLayout) findViewById(R.id.stageLayout);
    }

}
