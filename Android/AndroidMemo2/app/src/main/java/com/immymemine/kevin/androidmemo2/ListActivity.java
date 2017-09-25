package com.immymemine.kevin.androidmemo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.immymemine.kevin.androidmemo2.domain.Note;
import com.immymemine.kevin.androidmemo2.domain.NoteDAO;

import java.util.ArrayList;

/**
 * 안드로이드 sqlite DB 사용하기
 * 1. db 파일을 직접 코드로 생성
 * 2. 로컬에서 만든 파일을 assets 에 담은 후 복사 붙여넣기
 *    > ex) 우편번호처럼 기반 데이터가 필요한 db 일 경우...
 */

public class ListActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTitle, editContent;
    Button create, read, update, delete;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        // 1. DB 연결
//        DBHelper helper = new DBHelper(this);
//        SQLiteDatabase con = helper.getWritableDatabase();
//
//        // 2. 데이터 넣기
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
///        String datetime = sdf.format(new Date(System.currentTimeMillis()));
//
//        String insertQuery = "insert into note(title, content, n_datetime)" +
//                "values('제목 테스트','내용', datetime())";
//        con.execSQL(insertQuery);
//
//        con = helper.getReadableDatabase();
//        String selectQuery = "select id from note";
//        Cursor cursor = con.rawQuery(selectQuery, null);
//        String[] temp = cursor.getColumnNames();
//
//
//        cursor.close();
//        con.close();
//        helper.close();

        initView();
        initListener();
        init();
    }

    NoteDAO noteDAO;
    private void init() {
        noteDAO = new NoteDAO(this);
    }
    private Note getNoteFromScreen() {
        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();
        // Note 객체를 생성해서 값을 담아준다
        Note note = new Note(title, content);
        return note;
    }
    public void create(Note note) {
        noteDAO.create(note);
    }
//  -----------------------------------------------------------------------
    private void afterCreate() {
        // 등록 완료 Noti
        showInfo("등록되었습니다");
        // 목록 갱신
        read();
        // 입력창 초기화
        resetScreen();
    }
    private void showInfo(String comment) {
        Toast.makeText(this, comment, Toast.LENGTH_LONG);
    }
    public void read() {
        String query = " select * from note";
        ArrayList<Note> list = noteDAO.read(query);
        resultView.setText("");
        for(Note note : list)
            resultView.append(note.toString());
    }
    private void resetScreen() {
        editTitle.setText("");
        editContent.setText("");
    }

    @Override
    protected void onDestroy() {
        noteDAO.close();
        super.onDestroy();
    }

    private void initView() {
        editTitle = (EditText) findViewById(R.id.editText);
        editContent = (EditText) findViewById(R.id.editText2);
        create = (Button) findViewById(R.id.create);
        read = (Button) findViewById(R.id.read);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        resultView = (TextView) findViewById(R.id.textView);
    }
    private void initListener() {
        create.setOnClickListener(this);
        read.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create:
                create(getNoteFromScreen());
                afterCreate();
                break;
            case R.id.read:
                read();
                break;
            case R.id.update:
                break;
            case R.id.delete:
                break;
        }
    }
}