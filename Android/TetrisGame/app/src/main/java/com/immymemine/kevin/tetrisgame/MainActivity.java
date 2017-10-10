package com.immymemine.kevin.tetrisgame;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity{

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
        controller = new Controller(this, setting, handler);
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
        changeVisibilityResetButton();
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1771) {
                changeVisibilityResetButton();
            }
        }
    };
    public void changeVisibilityResetButton() {
        // 메시지 보내고... Main 에서 처리?
        if(resetBtn.getVisibility()==View.INVISIBLE)
            resetBtn.setVisibility(View.VISIBLE);
        else
            resetBtn.setVisibility(View.INVISIBLE);
    }
}
