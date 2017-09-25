package com.immymemine.kevin.myandroidstudy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Inflation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_inflation);
        // 1. 기본 방법

        // 2. 시스템 서비스 기능을 요청해서 Inflater 를 사용하는 방법
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundColor(Color.CYAN);

        TextView textView = new TextView(this);
        textView.setText("Inflation");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.MAGENTA);
        textView.setTextSize(30);
        linearLayout.addView(textView);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // setContentView(linearLayout);

        // context 를 이용할 수 있다
        LayoutInflater inflater_another = LayoutInflater.from(this);

        // View.inflate(this, R.layout.activity_inflation, null)
        ConstraintLayout constraint = (ConstraintLayout)inflater.inflate(R.layout.activity_inflation, null);
        // 원하는 리소스를 직접 전개를 통해서 View 에 add 할 수 있다.
        TextView text = (TextView) View.inflate(this, R.layout.inflation_to_get_view_whenever_you_want, null);
        // TextView 에 Clickable = "true" > OnClickListener()
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(), Inflation2.class);
                //Intent intent = new Intent(v.getContext(), SetParameter.class);
                Intent intent = new Intent(v.getContext(), GradientPractice.class);
                startActivity(intent);
            }
        });
        constraint.addView(text);
        setContentView(constraint);
    }
}
