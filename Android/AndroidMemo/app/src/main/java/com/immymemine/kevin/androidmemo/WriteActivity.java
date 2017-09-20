package com.immymemine.kevin.androidmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.immymemine.kevin.androidmemo.domain.Memo;
import com.immymemine.kevin.androidmemo.utility.FileUtil;

public class WriteActivity extends AppCompatActivity {
    EditText editTitle, editAuthor, editContent;
    Button postBtn, backBtn;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        initView();
        initListener();

        Intent intent = getIntent();
        count = intent.getIntExtra("length", 0);
    }

    /**
     *  내용을 파일에 작성
     *  - 파일 쓰기
     *    내부저장소 - Internal : 개별 App 만 접근 가능, 파일 탐색기에서 보이지 않는다.
     *                            경로 = /data/data/Package_name/files
     *    외부저장소 - External : 모든 App 이 접근 가능 but, 권한 필요
     */
    // private final String DIR_INTR = "/data/data/com.immymemine.kevin.androidmemo/files/"; Java

    private void write(Memo memo) {
        String filename = memo.getNo()+".txt";
        try {
            FileUtil.write(this, filename, memo.toString());
//            Intent intent = new Intent();
//            intent.putExtra("Data Yo", 123456);
//            setResult(RESULT_OK, intent);
            setResult(RESULT_OK);
            Toast.makeText(this, "Post!", Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Toast.makeText(this, "Error =" + e.toString(), Toast.LENGTH_LONG).show();
        }
        finish();
    }
    /*
    private void write(Memo memo) {
        // 내용을 파일에 쓴다
        String fileName = count+".txt";
        // File file = new File(DIR_INTR+fileName); Java
        FileOutputStream fos = null;
        try {
            // FileOutputStream fos = new FileOutputStream(file); Java
            // fos.write(memo.toBytes());
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(memo.toBytes());
            Log.d("WriteActivity", memo.toString());
        } catch (Exception e) {
            Toast.makeText(this, "에러 = " + e.toString(), Toast.LENGTH_LONG).show();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        finish();
    }
    */

    private Memo getMemoFromScreen() {
        Memo memo = new Memo();
        memo.setNo(count+1);
        memo.setSubject(editTitle.getText().toString());
        memo.setAuthor(editAuthor.getText().toString());
        memo.setContent(editContent.getText().toString());
        return memo;
    }

    private void initView() {
        editTitle = (EditText)findViewById(R.id.editTitle);
        editAuthor = (EditText)findViewById(R.id.editAuthor);
        editContent = (EditText)findViewById(R.id.editContent);
        postBtn = (Button)findViewById(R.id.postBtn);
        backBtn = (Button)findViewById(R.id.backBtn);
    }
    private void initListener() {
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Memo memo = getMemoFromScreen();
                write(memo);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
