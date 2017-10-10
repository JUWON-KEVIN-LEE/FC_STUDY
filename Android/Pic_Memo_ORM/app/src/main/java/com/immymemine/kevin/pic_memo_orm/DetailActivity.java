package com.immymemine.kevin.pic_memo_orm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initListener();
        init();
        getDetail();
        setView();
    }

    Bundle bundle;
    private void init() {
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("picnote");
    }

    Button backBtn;
    TextView tvTitle, tvDatetime;
    ImageView tvContent;
    private void initView() {
        backBtn = (Button) findViewById(R.id.backBtn);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDatetime = (TextView) findViewById(R.id.tv_datetime);
        tvContent = (ImageView) findViewById(R.id.tv_content);
    }

    private void initListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    String title, datetime;
    Bitmap bitmap;
    private void getDetail() {
        title = (String) bundle.get("title");
        datetime = (String) bundle.get("datetime");
        byte[] temp = bundle.getByteArray("bitmap");
        bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
    }

    private void setView() {
        tvTitle.setText(title);
        tvDatetime.setText(datetime);
        tvContent.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        bitmap.recycle();
        super.onDestroy();
    }
}