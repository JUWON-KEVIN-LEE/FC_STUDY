package com.immymemine.kevin.myandroidstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SetParameter extends AppCompatActivity {
    Button bLeft, bRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_parameter);
        initView();
        initListener();
    }
    private void setParam(int left, int right) {
        LinearLayout.LayoutParams paramsLeft = (LinearLayout.LayoutParams)bLeft.getLayoutParams();
        paramsLeft.weight = left;
        bLeft.setLayoutParams(paramsLeft);

        LinearLayout.LayoutParams paramsRight = (LinearLayout.LayoutParams)bRight.getLayoutParams();
        paramsRight.weight = right;
        bRight.setLayoutParams(paramsRight);
    }

    private void initView() {
        bLeft = (Button) findViewById(R.id.bLeft);
        bRight = (Button) findViewById(R.id.bRight);
    }
    private void initListener() {
        bLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setParam(3, 1);
            }
        });

        bRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setParam(1, 3);
            }
        });
    }
}
