package com.immymemine.kevin.basiclayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Android 화면 구조             Fragment(화면 조각)
//App(어플) > Activity(화면 단위) > Layout > Widget
public class MainActivity extends AppCompatActivity { // Activity 기반 클래스를 상속받아서 구성된다.
    // Button btnFrame, btnLineaer, btnGrid, btnRelative; // AppCompatActivity findViewById(id)
    TextView txt; // 1. 사용할 객체들을 가져온다...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 0. layout xml file 을 memory 에 load...
        setContentView(R.layout.activity_main);
        // btnFrame = (Button)findViewById(R.id.buttonFrameLayout);
        // btnLineaer = (Button)findViewById(R.id.buttonLinearLayout);
        // btnGrid = (Button)findViewById(R.id.buttonGridLayout);
        // btnRelative = (Button)findViewById(R.id.buttonRelativeLayout);

        findViewById(R.id.buttonFrameLayout).setOnClickListener(onClickListener);
        findViewById(R.id.buttonLinearLayout).setOnClickListener(onClickListener);
        findViewById(R.id.buttonGridLayout).setOnClickListener(onClickListener);
        findViewById(R.id.buttonRelativeLayout).setOnClickListener(onClickListener);
        findViewById(R.id.buttonCalculator).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent = null;
        @Override
        public void onClick(View v) {

            // Activity < major components
            // 1. Intent [System 에 전달되는 Message 객체]
            String id = ((Button)v).getText().toString();

            Toast.makeText(MainActivity.this, id, Toast.LENGTH_LONG).show();

            switch (v.getId()) {
                case R.id.buttonFrameLayout:
                    intent = new Intent(MainActivity.this, FrameActivity.class);
                    break;
                case R.id.buttonGridLayout:
                    intent = new Intent(MainActivity.this, GridActivity.class);
                    break;
                case R.id.buttonLinearLayout:
                    intent = new Intent(MainActivity.this, LinearActivity.class);
                    break;
                case R.id.buttonRelativeLayout:
                    intent = new Intent(MainActivity.this, RelativeActivity.class);
                    break;
                case R.id.buttonCalculator:
                    intent = new Intent(MainActivity.this, CalcView.class);
                    break;
            }
            startActivity(intent);
        }
    };
}

/*
public class MainActivity extends AppCompatActivity { // Activity 기반 클래스를 상속받아서 구성된다.
    Button btn; // AppCompatActivity findViewById(id)
    TextView txt; // 1. 사용할 객체들을 가져온다...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 0. layout xml file 을 memory 에 load...
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        txt = (TextView)findViewById(R.id.txt);
        // 2. 이벤트 처리...
        btn.setOnClickListener(new View.OnClickListener() { // 3. 어떻게 처리할 것인가...
            @Override
            public void onClick(View v) { // View 에 위젯을 담는다. View - interface ?
                txt.setText("Hello Android!");
            }
        });
    }
}
*/