package com.immymemine.kevin.basiclist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent(); // startActivity 를 통해 넘어온 intent 를 꺼낸다
        // 1. intent 에서 값의 묶음인 Bundle 을 꺼내고 번들에서 값을 꺼낸다
        Bundle bundle = intent.getExtras();
        String result = bundle.getString("valueKey");

        // 2. intent 에서 바로 값을 꺼내는 방법
        // String directResult = intent.getStringExtra("valueKey");

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(result);
    }
}
