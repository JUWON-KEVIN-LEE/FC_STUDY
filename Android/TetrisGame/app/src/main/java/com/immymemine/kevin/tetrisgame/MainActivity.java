package com.immymemine.kevin.tetrisgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout container;
    Controller controller;

    private static final int ROWS = 18;
    private static final int COLUMNS = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGame();
        initView();

    }

    @Override
    protected void onResume() {
        controller.runGame();
        super.onResume();
    }

    @Override
    protected void onStop() {
        controller.stopGame();
        super.onStop();
    }
    Button resetBtn;
    private void initView() {
        container = (FrameLayout)findViewById(R.id.container);
        controller = new Controller(this, setting);
        // 그리기 전에 그려야 할 것들을 준비해놔야한다.
        container.addView(controller);
        resetBtn = (Button) findViewById(R.id.resetBtn);
    }

    private static Setting setting;
    private void setGame() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        setting = new Setting(width, height, COLUMNS, ROWS);
    }

    public void up(View view) {
        controller.up();
    }
    public void down(View view) {
        controller.down();
    }
    public void left(View view) {
        controller.left();
    }
    public void right(View view) {
        controller.right();
    }
    public void reset(View view) {
        controller.reset();
    }
}
