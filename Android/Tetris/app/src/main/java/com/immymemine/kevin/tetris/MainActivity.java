package com.immymemine.kevin.tetris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout mainLayout;
    Button downBtn, leftBtn, rightBtn, upBtn;
    Stage stage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        stage = new Stage(this, width, height*2/3, 15);
        stage.setBlock(0, 0, stage.unit_width, stage.unit_height); // block 생성
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.mainLayout);
        layout.addView(stage);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upBtn:
                stage.moveBlock(stage.x, stage.y - stage.unit_height);
                stage.invalidate();
                break;
            case R.id.downBtn:
                stage.moveBlock(stage.x, stage.y + stage.unit_height);
                stage.invalidate();
                break;
            case R.id.leftBtn:
                stage.moveBlock(stage.x - stage.unit_width, stage.y);
                stage.invalidate();
                break;
            case R.id.rightBtn:
                stage.moveBlock(stage.x + stage.unit_width, stage.y);
                stage.invalidate();
                break;
        }
    }
}
