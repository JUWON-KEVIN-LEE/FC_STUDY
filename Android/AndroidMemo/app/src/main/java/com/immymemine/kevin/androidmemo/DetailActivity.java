package com.immymemine.kevin.androidmemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.immymemine.kevin.androidmemo.domain.Memo;
import com.immymemine.kevin.androidmemo.utility.FileUtil;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {
    TextView textViewTitle, textViewAuthor, textViewDate, textViewContent;
    Button backBtn;
    Memo memo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        getDetail(position);

        setView();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDetail(int position) {
        String fileName = position + 1 + ".txt";
        Context context = getBaseContext();
        String text = null;

        try {
            text = FileUtil.readAllFromFile(context, fileName);
        } catch (IOException e) {
            Toast.makeText(context, "Error = " + e.toString(), Toast.LENGTH_LONG).show();
        }

        memo = new Memo();
        memo.setMemo(text);
    }

    private void initView() {
        textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        textViewAuthor = (TextView)findViewById(R.id.textViewAuthor);
        //textViewDate = (TextView)findViewById(R.id.textViewDate);
        textViewContent = (TextView)findViewById(R.id.textViewContent);
        backBtn = (Button)findViewById(R.id.backBtn);
    }

    private void setView() {
        String title = memo.getSubject() + "\n" + memo.getDateTime();
//        textViewTitle.setText(memo.getSubject() + "\n" + memo.getDateTime());
        SpannableStringBuilder ssb = new SpannableStringBuilder(title);
        ssb.setSpan(new AbsoluteSizeSpan(24),title.indexOf("2017"), title.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // setSpan(What? 무엇을 바꿀것인지..., 시작점 startIndex, 끝점 endIndex, index 의 포함 관계)
        // TODO gravity 가능?
        textViewTitle.append(ssb);
        textViewAuthor.setText(memo.getAuthor());
        textViewContent.setText(memo.getContent());
    }
}
