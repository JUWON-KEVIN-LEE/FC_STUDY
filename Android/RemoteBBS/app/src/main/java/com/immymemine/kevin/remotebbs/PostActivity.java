package com.immymemine.kevin.remotebbs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostActivity extends AppCompatActivity {
    EditText title, content;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    private void initiateView() {
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        save = findViewById(R.id.post_btn);
    }

    private void setOnClickListener() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post();
            }
        });
    }

    private void post() {
        String t = "";
        if(title != null) {
            t = title.getText().toString();
        }
        String c = "";
        if(content != null) {
            c = content.getText().toString();
        }

    }
}
