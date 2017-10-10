package com.immymemine.kevin.androidmemoorm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.immymemine.kevin.androidmemoorm.DAO.PicNote_DAO;
import com.immymemine.kevin.androidmemoorm.model.PicNote;

import java.util.List;

/**
 *  RecyclerView 를 사용한 목록 만들기
 *  0. 화면 정의
 *  1. Data 를 정의
 *  2. Adapter 를 재정의 [상속]
 *  3. 재정의한 Adapter 를 생성하며 Data 를 담는다
 *  4. Adapter 와 RecyclerView 컨테이너를 연결
 *  5. RecyclerView 에 LayoutManager 를 설정
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void clickedPostButton(View view) {
        // post 클릭시 draw 할 수 있는 화면으로 이동
        Intent intent = new Intent(this, DrawActivity.class);
        startActivity(intent);
    }

    private void init() {
        // DAO 초기화
        PicNote_DAO dao = new PicNote_DAO(this);
        // 데이터를 읽어와서 저장소에 저장
        List<PicNote> data = dao.readAll();
        // 아답터 초기화
        CustomAdapter adapter = new CustomAdapter(this);
        adapter.setData(data);
        // 아답터와 RecyclerView 와 연결
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        // RecyclerView 에 LayoutManager 를 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
